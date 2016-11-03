package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.logic.TopK;
import ulink.logic.Utility;

/**
 * Servlet implementation class Analysis
 */
@WebServlet("/Analysis")
public class Analysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analysis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		PrintWriter out = response.getWriter();
		TopK analysis = new TopK();
		Utility utility = new Utility();
		String startDate = utility.changeDateFormat(request.getParameter("startDate"));
		String endDate = utility.changeDateFormat(request.getParameter("endDate"));
		String team = request.getParameter("teamName");
		
		HashMap<String, Integer> analysisList = analysis.compareTeam(startDate,endDate,team);
		System.out.println(analysisList.toString());
		Gson gson = new Gson();
		
		//JsonArray result = (JsonArray) new Gson().toJsonTree(analysisList,new TypeToken<HashMap<String,Integer>>() {}.getType());
		 String arrayListToJson = gson.toJson(analysisList);
		
		
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
