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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.Client;
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayPatientsByIllnessSorting
 */
@WebServlet("/DisplayPatientsByIllnessSorting")
public class DisplayPatientsByIllnessSorting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPatientsByIllnessSorting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String direction = request.getParameter("direction");
		int ID = (int)session.getAttribute("ID");
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
			} else if (client.getAge() < 2){
				if (client.getAge()*12 >= condition.getAgeRequired()  && client.getEmail().length() > 0) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
