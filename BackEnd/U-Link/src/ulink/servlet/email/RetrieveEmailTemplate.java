package ulink.servlet.email;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class RetrieveEmailTemplate
 */
@WebServlet("/RetrieveEmailTemplate")
public class RetrieveEmailTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveEmailTemplate() {
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
		doGet(request, response);
		String templateName = request.getParameter("templateName");
		//String msg = request.getParameter("msg");
		DatabaseConnection connection = new DatabaseConnection();
		String msg = connection.retrieveEmailTemplate(templateName);

		PrintWriter out = response.getWriter();
		out.write(msg);
		out.flush();
		return;
	}

}
