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

import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllConditionInfant
 */
@WebServlet("/DisplayAllInfant")
public class DisplayAllConditionInfant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllConditionInfant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<Condition> conditionList = database.retrieveAllConditionInfant();
		System.out.print(conditionList);
		
		Gson gson = new Gson();
		
		JsonArray result = (JsonArray) new Gson().toJsonTree(conditionList,new TypeToken<List<Condition>>() {}.getType());
		  String arrayListToJson = gson.toJson(result);
		  System.out.print(arrayListToJson);
		
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
