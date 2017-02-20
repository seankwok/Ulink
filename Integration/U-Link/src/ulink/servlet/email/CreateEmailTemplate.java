package ulink.servlet.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class CreateEmailTemplate
 */
@WebServlet("/CreateEmailTemplate")
public class CreateEmailTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateEmailTemplate() {
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
		doGet(request, response);
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String templateName = request.getParameter("templateName");
		String msg = request.getParameter("msg");
		//System.out.println(msg[0]);
		//System.out.println(msg[2]);
		DatabaseConnection connection = new DatabaseConnection();

		ArrayList<String> templateList = connection.retrieveAllEmailTemplate();
		boolean check = true;
		for (int i = 0; i < templateList.size(); i++) {
			String temp = templateList.get(i);
			if (temp.equals(templateName)) {
				check = false;
			}
		}
		
		String status = "";
		if (check) {
			connection.createEmailTemplate(templateName, msg);
			status = "success";
		} else {
			status = "fail";
		}
		PrintWriter out = response.getWriter();
		out.write(status);
		out.flush();
		return;
	}

}
