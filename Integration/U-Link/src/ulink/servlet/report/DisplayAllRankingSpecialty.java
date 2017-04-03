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

import ulink.constructor.RankingDoctor;
import ulink.constructor.RankingSpecialty;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayAllRankingSpecialty
 */
@WebServlet("/DisplayAllRankingSpecialty")
public class DisplayAllRankingSpecialty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllRankingSpecialty() {
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
		ArrayList<RankingSpecialty> SpecialtyList;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Utility utility = new Utility();
		SpecialtyList = database.retrieveAllRankingSpecialty(utility.changeDateExportFormat(startDate), utility.changeDateExportFormat(endDate));
		

		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(SpecialtyList, new TypeToken<List<RankingSpecialty>>() {
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
