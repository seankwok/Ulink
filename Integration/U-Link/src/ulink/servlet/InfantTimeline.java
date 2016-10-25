package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class Infant
 */
@WebServlet("/InfantTimeline")
public class InfantTimeline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfantTimeline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Condition> conditionList = connection.retrieveAllTypeCondition("Infant");
		Utility utility = new Utility();
		TreeMap<Integer, ArrayList<String>> returnTypeList = utility.returnTypeList(conditionList);

		Gson gson = new Gson();

		PrintWriter out = response.getWriter();
		String arrayListToJson = gson.toJson(returnTypeList);

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
