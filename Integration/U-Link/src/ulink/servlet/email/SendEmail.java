package ulink.servlet.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

		// System.out.println("this is content type : " +
		// request.getCharacterEncoding());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		doGet(request, response);
		response.setContentType("text/html; charset=utf-8");

		Email emailServer = new Email();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userDetails");
		String msg = request.getParameter("msg");
		// msg = URLEncoder.encode( msg, "ISO-8859-1" ); // H%C3%A9l%C3%A8ne
		// msg = URLDecoder.decode( msg, "UTF-8" );
		// System.out.println(msg);
		String[] email = (String[]) session.getAttribute("emailList");
		int ID = (int) session.getAttribute("ID");
		DatabaseConnection connection = new DatabaseConnection();
		Condition condition = connection.retrieveAllConditionByID(ID);
		String subject = request.getParameter("subject");
		String CC = request.getParameter("CC");
		msg = msg.replaceAll("&nbsp;", "<br>");
		String temp = "";

		email = email[0].split(",");
		// System.out.println(msg + " TEST ");
		boolean check = true;
		if (msg != null) {
			for (int i = 0; i < email.length; i++) {
				temp = msg.replace("[screening]", condition.getScreening());
				temp = temp.replace("[clientName]", connection.getNameByEmail(email[i]));

				temp = temp.replace("[clientEmail]", email[i]);

				// System.out.println(email[i] + " email");
				// System.out.println(subject + " subject");
				// System.out.println(temp + " temp");
				// System.out.println(user.getEmail() + " userEmail");

				check = emailServer.sendEmail(email[i], CC, subject, temp, user.getEmail()+"@ulinkassist.com");
				//check = emailServer.sendEmail(email[i], CC, subject, temp, "nabilahbmnk.2014@sis.smu.edu.sg");
				// check = emailServer.sendEmail(email[i], subject, temp,
				// "kaixin.teh.2014@sis.smu.edu.sg");
				// check = emailServer.sendEmail(email[i], subject, temp,
				// "sychien.2014@sis.smu.edu.sg");
				// Client name, screening, date Email,
				TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
				
				Date date2 = new Date();
				
				String datetime = date2.getDate() + "/" + (date2.getMonth()+1) + "/" + (date2.getYear() + 1900) + " "
						+ date2.getHours() + ":" + date2.getMinutes();

				connection.createEmailDate(connection.getNameByEmail(email[i]), condition.getScreening(), datetime);
			}
		} else {
			check = false;
		}
		String status = "";
		if (check) {
			status = "" + email.length;
		} else {
			status = "fail";
		}

		PrintWriter out = response.getWriter();
		out.write(status);
		out.flush();
		return;
	}

}
