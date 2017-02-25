package ulink.servlet.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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
 * Servlet implementation class DisplayIndexMeanSD
 */
@WebServlet("/DisplayIndexMeanSD")
public class DisplayIndexMeanSD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayIndexMeanSD() {
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
		Utility utility = new Utility();
		ArrayList<Index> indexList = connection.retrieveAllIndex(startDate, endDate, team);
		//HashMap<Integer,Double> pointSystem = utility.getIndexCount(indexList);
		//String mean = utility.countMean(pointSystem, indexList.size());
		//String SD = utility.countSD(pointSystem, indexList.size());
		
		HashMap<String,String> calculation = new HashMap<>();
		//calculation.put("mean", mean);
		//calculation.put("SD", SD);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String arrayListToJson = gson.toJson(calculation);
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
