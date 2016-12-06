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
 * Servlet implementation class TopDoctor
 */
@WebServlet("/TopResult")
public class TopResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TopK topK = new TopK();
		HashMap<String, Integer> topList;
		String endDate = request.getParameter("endDate");
		String startDate = request.getParameter("startDate");
		String type = request.getParameter("type");
		
		Utility utility = new Utility();
		
		startDate = utility.changeDateFormat(startDate);
		endDate = utility.changeDateFormat(endDate);
		System.out.print(type);
		if (type.equals("doctor")){
			topList = topK.topDoctor(startDate,endDate);
		} else if (type.equals("Referral")){
			topList = topK.topReferral(startDate, endDate);
		}else {
			topList = topK.topSpeciality(startDate, endDate);
		}
		
		System.out.println(startDate + "   "  + endDate);
		Gson gson = new Gson();

		PrintWriter out = response.getWriter();
		String arrayListToJson = gson.toJson(topList);

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
