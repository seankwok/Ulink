package ulink.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import ulink.logic.Utility;


/**
 * Servlet implementation class LoginServlet
 */
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
			
		Utility utility = new Utility();
		boolean isValid = utility.loginValidatation(username, password);
		HttpSession session=request.getSession();  
		
		if (isValid){		
			session.setAttribute("admin",username);  
			response.sendRedirect("./index.html");
			return;
			//request.getRequestDispatcher("./ToDoServlet").forward(request, response);
		} else {
			request.setAttribute("error", "Invalid username/password");
			request.getRequestDispatcher("Login.html").forward(request, response);
			return;
		}
		
	}

}
