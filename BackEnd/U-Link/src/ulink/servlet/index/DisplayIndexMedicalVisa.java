package ulink.servlet.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayIndexMedicalVisa
 */
@WebServlet("/DisplayIndexMedicalVisa")
public class DisplayIndexMedicalVisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayIndexMedicalVisa() {
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
		Utility utility = new Utility();
		ArrayList<Index> indexList = connection.retrieveAllIndex(utility.changeDateFormatDatabase(startDate), utility.changeDateFormatDatabase(endDate), team);
		LinkedHashMap<Integer,Double> pointSystem = utility.getIndexCount(indexList);
		Gson gson = new Gson();
	//	JsonArray result = (JsonArray) new Gson().toJsonTree(pointSystem, new TypeToken<LinkedHashMap<Integer,Integer>>() {
		//}.getType());
		PrintWriter out = response.getWriter();
		String arrayListToJson = gson.toJson(pointSystem);
	//	System.out.print(arrayListToJson);
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
