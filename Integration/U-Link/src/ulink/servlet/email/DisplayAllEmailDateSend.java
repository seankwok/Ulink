package ulink.servlet.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Condition;
import ulink.constructor.EmailSend;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllEmail
 */
@WebServlet("/DisplayAllEmailDateSend")
public class DisplayAllEmailDateSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllEmailDateSend() {
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
		String clientName = request.getParameter("clientName");
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<EmailSend> emailSendList = connection.retrieveEmailSendDetails(clientName);


		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		JsonArray result = (JsonArray) new Gson().toJsonTree(emailSendList,new TypeToken<List<EmailSend>>() {}.getType());
		  String arrayListToJson = gson.toJson(result);
		  
		
		out.write(arrayListToJson);
		out.flush();
		return;
	}

}
