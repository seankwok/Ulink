package ulink.servlet.email;

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

import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayAllEmailTemplate
 */
@WebServlet("/DisplayAllEmailTemplate")
public class DisplayAllEmailTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllEmailTemplate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<String> emailList = connection.retrieveAllEmailTemplate();


		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		JsonArray result = (JsonArray) new Gson().toJsonTree(emailList,new TypeToken<List<String>>() {}.getType());
		  String arrayListToJson = gson.toJson(result);
		  
		
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
