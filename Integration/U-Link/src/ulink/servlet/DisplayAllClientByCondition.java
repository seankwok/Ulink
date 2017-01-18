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
import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllClientByCondition
 */
@WebServlet("/DisplayAllClientByCondition")
public class DisplayAllClientByCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllClientByCondition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		int ID = Integer.parseInt(request.getParameter("ID"));
		Condition condition = connection.retrieveAllConditionByID(ID);
		ArrayList<Client> newClientList = new ArrayList<>();
		
		for (int i =0; i<clientList.size(); i++){
			Client client = clientList.get(i);
			if (client.getAge() > condition.getAgeRequired()){
				newClientList.add(client);
				}
		}
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		JsonArray result = (JsonArray) new Gson().toJsonTree(newClientList, new TypeToken<List<Client>>() {
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
