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

import ulink.constructor.KPI;
import ulink.logic.TopK;
import ulink.logic.Utility;

/**
 * Servlet implementation class KPIVisa
 */
@WebServlet("/KPIVisa")
public class KPIVisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KPIVisa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String thisYearLastMonth = request.getParameter("thisYearLastMonth");
		String lastYearThisMonth = request.getParameter("lastYearThisMonth");
		String lastYearLastMonth = request.getParameter("lastYearLastMonth");
		TopK topk = new TopK();
		int year = Integer.parseInt(date.substring(0, 4));
		String month = date.substring(5);
		//System.out.println("TEST " + month);
		Utility utility = new Utility();
		String startDate = utility.getStartDateOfMonth(year+"-"+month+"-"+"01");
		String endDate = utility.getEndDateOfMonth(year+"-"+month+"-"+"01");
		int lastMonthDate = Integer.parseInt(thisYearLastMonth.substring(5));
		int lastMonthYear = Integer.parseInt(thisYearLastMonth.substring(0,4));
		int lastYearDate = Integer.parseInt(lastYearThisMonth.substring(0, 4));
		String startDatelastMonth = utility.getStartDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String endDatelastMonth = utility.getEndDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String startDateLastYear = utility.getStartDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		String endDatelastYear = utility.getEndDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		
		//System.out.println(startDate + " " + endDate);
		
		KPI kpi = topk.getKPIVisa(type, startDate,endDate);
		KPI lastMonth = topk.getKPIVisa(type, startDatelastMonth,endDatelastMonth);
		KPI lastyear = topk.getKPIVisa(type,startDateLastYear,endDatelastYear);
		//KPI LMLY = topk.getKPI(type,lastYearLastMonth);
		
		ArrayList<KPI> kpiList = new ArrayList<>();
		kpiList.add(kpi);
		kpiList.add(lastMonth);
		double outChange = 0;
		double inChange = 0;
		
		if (lastMonth.getInPatient() != 0){
			inChange = (1.0*kpi.getInPatient()-lastMonth.getInPatient())/lastMonth.getInPatient()*100;
		}
		
		if (lastMonth.getOutPatient() != 0){
			outChange = (1.0*kpi.getOutPatient()-lastMonth.getOutPatient())/lastMonth.getOutPatient()*100;
		}
		kpiList.add(new KPI("Increase\\Decrease (%)",Math.round(inChange),Math.round(outChange)));
		kpiList.add(kpi);
		kpiList.add(lastyear);
		
		
		inChange = 0;
		outChange = 0;
		
		if (lastyear.getInPatient() != 0){
			inChange = (1.0*kpi.getInPatient()-lastyear.getInPatient())/lastyear.getInPatient()*100;
		} else {
			inChange = 0;
		}
		
		if (lastyear.getOutPatient() != 0){
			outChange = (1.0*kpi.getOutPatient()-lastyear.getOutPatient())/lastyear.getOutPatient()*100;
		} else {
			outChange = 0;
		}
		kpiList.add(new KPI("Increase\\Decrease (%)",Math.round(inChange),Math.round(outChange)));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
