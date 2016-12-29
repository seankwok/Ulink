package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Client;
import ulink.constructor.User;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllUser
 */
@WebServlet("/DisplayAllUser")
public class DisplayAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<User> userList = connection.getUser();

		//JsonArray result = (JsonArray) new Gson().toJsonTree(userList, new TypeToken<List<User>>() {}.getType());
		
		
		//String json = new Gson().toJson(userList);
		request.setAttribute("userList", userList);
		RequestDispatcher rd=request.getRequestDispatcher("accountManagement.jsp");  
		rd.forward(request, response);
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}