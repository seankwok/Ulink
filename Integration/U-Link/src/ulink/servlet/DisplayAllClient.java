package ulink.servlet;

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

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllClient
 */
@WebServlet("/DisplayAllClient")
public class DisplayAllClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayAllClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		DatabaseConnection database = new DatabaseConnection();
		ArrayList<Client> clientList;
		String headerName = request.getParameter("headerName");
		String order = request.getParameter("order");
		
		if (headerName == null && order == null){
			clientList = database.retrieveAllClientList();
		} else if (order == null){
			clientList = database.retrieveAllClientListByOrder(headerName, "ASC");
		} else {
			clientList = database.retrieveAllClientListByOrder(headerName, order);
		}
	
		
		

		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(clientList, new TypeToken<List<Client>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
		out.write(arrayListToJson);
		out.flush();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
