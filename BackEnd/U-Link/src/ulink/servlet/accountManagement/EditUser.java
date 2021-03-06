package ulink.servlet.accountManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		HttpSession session = request.getSession();
		String roles = (String) session.getAttribute("admin");
		if (roles.equals("admin")){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		String hPassword = utility.hash(password);
		//System.out.println("TETETET" + hPassword);
		connection.editUser(email, hPassword);
		
		
		PrintWriter out = response.getWriter();
		String jsonInString = "{\"status\":\"success\"}";
		out.write(jsonInString);
		out.flush();
		return;
		} else {
			response.sendRedirect("./index.html");
			return;
		}
	}

}
