package ulink.servlet.accountManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ulink.constructor.User;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DatabaseConnection connection = new DatabaseConnection();
		
		Utility utility = new Utility();
		boolean isValid = utility.loginValidatation(username, password);
		HttpSession session=request.getSession();  
		
		PrintWriter out = response.getWriter();
		if (isValid){		
			User user = connection.getUserByEmail(username);
			session.setAttribute("admin", user.getRoles());
			session.setAttribute("userDetails", user);
			System.out.print("Test");
			String jsonInString = "success";
			out.write(jsonInString);
			out.flush();
			return;
			//session.setAttribute("admin",username);  
			//response.sendRedirect("./index.html");
			//return;
			//request.getRequestDispatcher("./ToDoServlet").forward(request, response);
		} 
		
	}

}
