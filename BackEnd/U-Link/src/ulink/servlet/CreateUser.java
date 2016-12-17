package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class CreateUser
 */
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		if (password.equals(repeatPassword)) {
			String hPassword = utility.hash(password);
			connection.createUser(username, hPassword);
		} else {
			response.sendRedirect("./Login.html");
			String jsonInString = "{\"status\":\"fail\"}";
			out.write(jsonInString);
			out.flush();
			return;
		}
		String jsonInString = "{\"status\":\"success\"}";

		out.write(jsonInString);

		out.flush();
		return;

	}

}
