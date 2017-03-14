package ulink.servlet.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Client;
import ulink.constructor.Index;
import ulink.constructor.PersonInCharge;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayIndexMedicalVisaByPersonInCharge
 */
@WebServlet("/DisplayIndexMedicalVisaByPersonInCharge")
public class DisplayIndexMedicalVisaByPersonInCharge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayIndexMedicalVisaByPersonInCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String team = request.getParameter("team");
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		Utility utility = new Utility();
		ArrayList<PersonInCharge> listAllPIC = new ArrayList<>();

		for (int i = 0; i < personInChargeList.size(); i++) {
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(
					utility.changeDateFormatDatabase(startDate), utility.changeDateFormatDatabase(endDate), team, temp);
			LinkedHashMap<Integer, Double> pointSystem = utility.getIndexCount(indexList);
			listAllPIC.add(new PersonInCharge(temp, pointSystem));
		}
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(listAllPIC, new TypeToken<List<PersonInCharge>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
		// System.out.print(arrayListToJson);

		out.write(arrayListToJson);
		out.flush();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
