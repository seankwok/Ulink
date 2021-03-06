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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Client;
import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayConditionByClient
 */
@WebServlet("/DisplayConditionByClient")
public class DisplayConditionByClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayConditionByClient() {
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
		HttpSession session = request.getSession();
		String clientName = (String) session.getAttribute("clientName");
		DatabaseConnection connection = new DatabaseConnection();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		ArrayList<Condition> newConditionList = new ArrayList<>();
	//	System.out.println(clientName + " Name");
		ArrayList<Client> clientList = connection.retrieveAllClientByName(clientName);
	//	System.out.println(clientList.size() + " Size");
		if (clientList.size() > 0) {
			Client client = clientList.get(0);
			ArrayList<Condition> conditionList = connection.retrieveAllCondition("ID", "ASC");
		//	System.out.println(conditionList.size() + " List");


			for (int i = 0; i < conditionList.size(); i++) {
				Condition condition = conditionList.get(i);
				if (condition.getAgeRequired() <= client.getAge()
						&& condition.getType().toUpperCase().equals(client.getGender().toUpperCase())) {
					newConditionList.add(condition);
				}
			}

		}

		JsonArray result = (JsonArray) new Gson().toJsonTree(newConditionList, new TypeToken<List<Condition>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
		//System.out.print(arrayListToJson + " RQEQWEWQ");

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
