package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		TopK topk = new TopK();
		KPI kpi = topk.getKPI(type, date);
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();

		String arrayListToJson = gson.toJson(kpi);
		//System.out.print(arrayListToJson);

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
