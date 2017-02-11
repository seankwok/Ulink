package ulink.servlet.accountManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ulink.constructor.Client;
import ulink.constructor.User;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
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
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("admin");
		
		if (role.equals("admin")){
		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		PrintWriter out = response.getWriter();
			boolean check = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roles = request.getParameter("roles");
		ArrayList<User> userList = connection.getUser();	
		
		for (int i = 0; i < userList.size(); i++){
			if (userList.get(i).getEmail().equals(username)){
				check = true;
			}	
		}
		if (!check){
		String hPassword = utility.hash(password);
		connection.createUser(username, hPassword,roles);
		String jsonInString = "success";
			out.write(jsonInString);
			out.flush();
			return;
		}else {
			String jsonInString = "fail";
			out.write(jsonInString);
			out.flush();
			return;
		}

	}else {
		response.sendRedirect("./index.html");
		return;
	}
	}
}
