package ulink.servlet.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayDashboardVisa
 */
@WebServlet("/DisplayDashboardVisa")
public class DisplayDashboardVisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDashboardVisa() {
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
		String date = connection.retrieveLatestDate();
		Utility utility = new Utility();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);
		
		ArrayList<String> list = connection.retrievePastSixMonthRecord("Visa", startDate, endDate);
		LinkedHashMap<String,Integer> pastSixMonth = new LinkedHashMap<>();
		
		
		
		for (int i =0; i < list.size(); i++){
			
			//System.out.println(utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7))));
			String month = utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7)));
			if (pastSixMonth.containsKey(month)){
				int temp = pastSixMonth.get(month);
				pastSixMonth.put(month, temp+1);
			} else {
				pastSixMonth.put(month, 1);
			}
		}
		
		Gson gson = new Gson();
		String arrayListToJson = gson.toJson(pastSixMonth);
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
