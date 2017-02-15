package ulink.servlet.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Condition;
import ulink.constructor.RankingReferredBy;
import ulink.constructor.User;
import ulink.dao.DatabaseConnection;
import ulink.logic.Email;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmail() {
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
		
		Email emailServer = new Email();
		HttpSession session = request.getSession();
		// System.out.println(request.getContentType());
		
		String[] email = (String[]) session.getAttribute("emailList");
		int ID = (int) session.getAttribute("ID");
		DatabaseConnection connection = new DatabaseConnection();
		Condition condition = connection.retrieveAllConditionByID(ID);
		String subject = request.getParameter("subject");
		String msg = request.getParameter("msg");

		String temp = "";
		User user = (User) session.getAttribute("userDetails");
		email = email[0].split(",");
		System.out.println(msg + " TEST ");
		boolean check = true;
		if (msg != null) {
			for (int i = 0; i < email.length; i++) {
				temp = msg.replace("[screening]", condition.getScreening());
				temp = temp.replace("[clientName]", connection.getNameByEmail(email[i]));

				temp = temp.replace("[clientEmail]", email[i]);

				System.out.println(email[i] + " email");
				System.out.println(subject + " subject");
				System.out.println(temp + " temp");
				System.out.println(user.getEmail() + " userEmail");

				 check = emailServer.sendEmail(email[i], subject, temp, user.getEmail()+"@ulinkassist.com");
				//check = emailServer.sendEmail(email[i], subject, temp, "seankwok794@hotmail.com");

			}
		} else {
			check = false;
		}
		String status = "";
		if (check) {
			status = "pass";
		} else {
			status = "fail";
		}

		PrintWriter out = response.getWriter();
		out.write(status);
		out.flush();
		return;
	}

}
