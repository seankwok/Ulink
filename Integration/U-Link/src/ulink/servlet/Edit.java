package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
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
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		HttpSession session = request.getSession();
		String conditionId = (String) session.getAttribute("ID");
		Condition conditionType = connection.retrieveConditionDetails(Integer.parseInt(conditionId));

		int count = 0;
		int age = Integer.parseInt(request.getParameter("age"));
		String illness = request.getParameter("illness");
		String screening = request.getParameter("screening");
		String[] types = request.getParameterValues("type");
		String years = request.getParameter("years");
		ArrayList<Condition> conditionList = connection.retrieveAllCondition();
		String[] type = types[0].split(",");
		
		for (int i = 0; i < type.length; i++) {
			boolean check = true;
			for (int k = 0; k < conditionList.size(); k++) {
				//Condition condition = conditionList.get(k);
				connection.editAllCondition(Integer.parseInt(conditionId),illness, years, age, screening, type[i]);
			}
			
		}
		//connection.deleteAllCondition(Integer.parseInt(conditionId));
		String jsonInString;
		if (count == 0) {
			jsonInString = "success";
		} else {
			jsonInString = "fail";
		}
		out.write(jsonInString);
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
