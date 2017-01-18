package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
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
 * Servlet implementation class DisplayAll
 */
@WebServlet("/DisplayAll")

public class DisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String gender = request.getParameter("gender");
		//String order = request.getParameter("order");
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<Condition> conditionList; 
		if (gender.equals("All")){
			conditionList = database.retrieveAllCondition();
		} else if (gender.equals("Female")){
			conditionList = database.retrieveAllConditionBySort(gender);
		} else {
			conditionList = database.retrieveAllConditionBySort(gender);
		}
	
		 
		
		
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
