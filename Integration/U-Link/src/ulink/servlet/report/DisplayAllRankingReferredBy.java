package ulink.servlet.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.RankingReferredBy;
import ulink.constructor.RankingSpecialty;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayAllRankingReferredBy
 */
@WebServlet("/DisplayAllRankingReferredBy")
public class DisplayAllRankingReferredBy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllRankingReferredBy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<RankingReferredBy> referredByList;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		System.out.println(startDate);
		//dd/mm/yyyy >> yyyy/mm/dd
		Utility utility = new Utility();
		referredByList = database.retrieveAllRankingReferredBy(utility.changeDateExportFormat(startDate), utility.changeDateExportFormat(endDate));
		

		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(referredByList, new TypeToken<List<RankingReferredBy>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
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
