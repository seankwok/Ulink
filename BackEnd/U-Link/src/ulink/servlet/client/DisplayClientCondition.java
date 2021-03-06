package ulink.servlet.client;

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
import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayClientCondition
 */
@WebServlet("/DisplayClientCondition")
public class DisplayClientCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayClientCondition() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		String clientName = request.getParameter("clientName");
		ArrayList<Client> clientList = connection.retrieveAllClientByName(clientName);
		Client client = clientList.get(0);

		ArrayList<Condition> conditionList = connection.retrieveConditionListByAge(client.getAge());
		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(conditionList, new TypeToken<List<Condition>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
		System.out.print(arrayListToJson);

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
