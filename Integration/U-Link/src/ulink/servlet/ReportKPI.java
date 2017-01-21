package ulink.servlet;

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

import ulink.constructor.Condition;
import ulink.constructor.KPI;
import ulink.constructor.RankingSpecialty;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;

/**
 * Servlet implementation class ReportKPI
 */
@WebServlet("/ReportKPI")
public class ReportKPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportKPI() {
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
		//DatabaseConnection connection = new DatabaseConnection();
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String thisYearLastMonth = request.getParameter("thisYearLastMonth");
		String lastYearThisMonth = request.getParameter("lastYearThisMonth");
		String lastYearLastMonth = request.getParameter("lastYearLastMonth");
		TopK topk = new TopK();
		int year = Integer.parseInt(date.substring(0, 5));
		int month = Integer.parseInt(date.substring(5));
		
		KPI kpi = topk.getKPI(type, date);
		KPI lastMonth = topk.getKPI(type, thisYearLastMonth);
		KPI lastyear = topk.getKPI(type,lastYearThisMonth);
		KPI LMLY = topk.getKPI(type,lastYearLastMonth);
		
		ArrayList<KPI> kpiList = new ArrayList<>();
		kpiList.add(kpi);
		kpiList.add(lastMonth);
		int outChange = 0;
		int inChange = 0;
		
		if (lastMonth.getInPatient() != 0){
			inChange = (kpi.getInPatient()-lastMonth.getInPatient())/lastMonth.getInPatient();
		}
		
		if (lastMonth.getOutPatient() != 0){
			outChange = (kpi.getOutPatient()-lastMonth.getOutPatient())/lastMonth.getOutPatient();
		}
		kpiList.add(new KPI("Increase\\Decrease",inChange,outChange));
		kpiList.add(lastyear);
		kpiList.add(LMLY);
		
		if (LMLY.getInPatient() != 0){
			inChange = (lastyear.getInPatient()-LMLY.getInPatient())/LMLY.getInPatient();
		} else {
			inChange = 0;
		}
		
		if (lastMonth.getOutPatient() != 0){
			outChange = (lastyear.getOutPatient()-LMLY.getOutPatient())/LMLY.getOutPatient();
		} else {
			outChange = 0;
		}
		kpiList.add(new KPI("Increase\\Decrease",inChange,outChange));
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(kpiList, new TypeToken<List<KPI>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
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
