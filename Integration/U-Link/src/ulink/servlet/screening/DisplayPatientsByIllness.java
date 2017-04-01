package ulink.servlet.screening;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
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
		//int age = Integer.parseInt(request.getParameter("age"));
		//String gender = request.getParameter("gender");
		//String type = request.getParameter("type");
		int ID = Integer.parseInt(request.getParameter("ID"));
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String direction = request.getParameter("direction");
		session.setAttribute("ID", ID);
		Condition condition = connection.retrieveAllConditionByID(ID);
		
		ArrayList<Client> clientList = connection.retrieveAllClientListEmail(name, direction);
		ArrayList<ClientByIllness> clientByIllnessList = new ArrayList<>();
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if ((condition.getType().toLowerCase().equals("male") || condition.getType().toLowerCase().equals("female")) && client.getEmail().length() > 0) {
				if (client.getAge() >= condition.getAgeRequired() && client.getGender().toLowerCase().equals(condition.getType().toLowerCase())) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
							client.getEmail(), client.getGender(),condition.getScreening(), condition.getConditionName(), connection.retrieveLatestDateSend(client.getClientName(),condition.getScreening()) ,client.getFollowUpPerson()));
					//System.out.println("TQEQWEQWEQW " + connection.retrieveLatestDateSend(client.getClientName(),condition.getScreening()));
				}
			} else if (client.getAge() <= 2 && client.getDateOfBirth().length() > 0){
				String date = client.getDateOfBirth();
				//System.out.println(date + "qwewqewqewq");
				int day = Integer.parseInt(date.substring(0,2));
				int month = Integer.parseInt(date.substring(3,5)) - 1;  
				int year = Integer.parseInt(date.substring(6)) + 1900; 
				
				Date current = new Date();
				Date dob = new Date(day,month,year);
				
				int months = (current.getMonth() - dob.getMonth()) + (current.getYear() - dob.getYear()) * 12 ;
				
				if (months >= condition.getAgeRequired()) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), months,
							client.getEmail(), client.getGender(),condition.getScreening(), condition.getConditionName(), connection.retrieveLatestDateSend(client.getClientName(), condition.getScreening()), client.getFollowUpPerson()));

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
