package ulink.servlet.screening;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		int count = 0;
		int age = Integer.parseInt(request.getParameter("age"));
		String illness = request.getParameter("illness");
		String screening = request.getParameter("screening");
		String[] types = request.getParameterValues("type");
		String years = request.getParameter("years");
		ArrayList<Condition> conditionList = connection.retrieveAllCondition("ID", "ASC");
		System.out.println(screening);
		String[] type = types[0].split(",");
		for (int i = 0; i < type.length; i++) {
			boolean check = true;
			for (int k = 0; k < conditionList.size(); k++) {
				Condition condition = conditionList.get(k);
				if (condition.getConditionName().toLowerCase().trim().equals(illness.toLowerCase().trim()) && condition.getType().toLowerCase().trim().equals(type[i].toLowerCase().trim()) && condition.getScreening().toLowerCase().trim().equals(screening.toLowerCase().trim())){
			
					check = false;
				}
			}
			if (check) {
				connection.addAllCondition(illness, years, age, screening, type[i]);
			} else {
				count++;
			}
		}

		System.out.println(illness);
		String jsonInString;
		if (count == 0){
		jsonInString = "success";
		} else{
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
