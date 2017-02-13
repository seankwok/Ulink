package ulink.servlet.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.constructor.Index;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String team = request.getParameter("team");
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		Utility utility = new Utility();
		LinkedHashMap<String,LinkedHashMap<Integer,Integer>> personInChargePointSystem = new  LinkedHashMap<String,LinkedHashMap<Integer,Integer>>();
		for (int i = 0; i<personInChargeList.size(); i++){
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(utility.changeDateFormatDatabase(startDate), utility.changeDateFormatDatabase(endDate), team,temp);	
			LinkedHashMap<Integer,Integer> pointSystem = utility.getIndexCount(indexList);
			if (!personInChargePointSystem.containsKey(temp)){
				personInChargePointSystem.put(temp, pointSystem);
			}
		}
		
		
		Gson gson = new Gson();
	//	JsonArray result = (JsonArray) new Gson().toJsonTree(pointSystem, new TypeToken<LinkedHashMap<Integer,Integer>>() {
		//}.getType());
		PrintWriter out = response.getWriter();
		String arrayListToJson = gson.toJson(personInChargePointSystem);
	//	System.out.print(arrayListToJson);
		out.write(arrayListToJson);
		out.flush();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
