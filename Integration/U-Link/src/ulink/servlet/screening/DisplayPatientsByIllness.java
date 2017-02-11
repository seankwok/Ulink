package ulink.servlet.screening;

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
import ulink.constructor.ClientByIllness;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayPatientsByIllness
 */
@WebServlet("/DisplayPatientsByIllness")
public class DisplayPatientsByIllness extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayPatientsByIllness() {
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
		DatabaseConnection connection = new DatabaseConnection();
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String type = request.getParameter("type");
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		ArrayList<ClientByIllness> clientByIllnessList = new ArrayList<>();
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (type.equals("adult")) {
				if (client.getAge() >= age && client.getGender().equals(gender)) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
							client.getEmail(), client.getGender()));
				}
			} else {
				if (client.getAge() >= age / 12 && client.getGender().equals(gender)) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
							client.getEmail(), client.getGender()));

				}
			}
		}
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		JsonArray result = (JsonArray) new Gson().toJsonTree(clientByIllnessList, new TypeToken<List<ClientByIllness>>() {
		}.getType());
		String arrayListToJson = gson.toJson(result);
		//System.out.print(arrayListToJson);

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