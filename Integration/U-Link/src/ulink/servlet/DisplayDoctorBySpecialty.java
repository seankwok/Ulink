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


import ulink.constructor.*;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class DisplayDoctorBySpecialty
 */
@WebServlet("/DisplayDoctorBySpecialty")
public class DisplayDoctorBySpecialty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDoctorBySpecialty() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String specialty = request.getParameter("Specialty");
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<RankingDoctorSpecialty> doctorList = connection.retrieveAllDoctorBySpecialty(specialty);
		
		Gson gson = new Gson();

		JsonArray result = (JsonArray) new Gson().toJsonTree(doctorList, new TypeToken<List<RankingDoctorSpecialty>>() {
		}.getType());
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
