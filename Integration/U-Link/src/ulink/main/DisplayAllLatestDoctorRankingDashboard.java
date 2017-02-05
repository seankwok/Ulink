package ulink.main;

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

import ulink.constructor.RankingDoctor;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllLatestDoctorRankingDashboard
 */
@WebServlet("/DisplayAllLatestDoctorRankingDashboard")
public class DisplayAllLatestDoctorRankingDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllLatestDoctorRankingDashboard() {
        super();
        // TODO Auto-generated constructor stub
        
        
		
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();

		DatabaseConnection connection = new DatabaseConnection();

		
		ArrayList<RankingDoctor> list = connection.retrieveAllRankingDoctorDashBoard(connection.retrieveLatestDate());
		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(list, new TypeToken<List<RankingDoctor>>() {
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
