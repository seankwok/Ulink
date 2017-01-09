package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		
		int age = Integer.parseInt(request.getParameter("age"));
		String illness = request.getParameter("illness");
		String screening = request.getParameter("screening");
		String [] types = request.getParameterValues("type");
		int years = Integer.parseInt(request.getParameter("years"));
		String [] type = types[0].split(",");
		for (int i=0; i<type.length; i++){
		connection.addAllCondition(illness, years, age, screening,type[i]);
		
		}
		
		System.out.println(illness);
		String jsonInString = "success";
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
