package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
import ulink.logic.AgeGender;

/**
 * Servlet implementation class DisplayAllGenderByAge
 */
@WebServlet("/DisplayAllGenderByAge")
public class DisplayAllGenderByAge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllGenderByAge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		AgeGender ageGender =new AgeGender();
		TreeMap<String,Integer> ageByGenderList = ageGender.genderByAge();
		
		Gson gson = new Gson();
		
		//JsonArray result = (JsonArray) new Gson().toJsonTree(conditionList,new TypeToken<List<Condition>>() {}.getType());
		  String arrayListToJson = gson.toJson(ageByGenderList);
		
		
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