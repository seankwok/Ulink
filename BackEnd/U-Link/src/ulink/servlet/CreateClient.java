package ulink.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class CreateClient
 */
@WebServlet("/CreateClient")
public class CreateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		
		String passportNumber = request.getParameter("passportNumber");
		String clientName = request.getParameter("clientName");
		String gender = request.getParameter("gender");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String mainDiagnosis = request.getParameter("mainDiagnosis");
		String clientType = request.getParameter("clientType");
		String nationality = request.getParameter("nationality");
		String countryOfResidence = request.getParameter("countryOfResidence");
		String billingStreet = request.getParameter("billingStreet");
		String billingCity = request.getParameter("billingCity");
		String billingState = request.getParameter("billingState");
		String billingCountry = request.getParameter("billingCountry");
		String billingCode = request.getParameter("billingCode");
		String isMedical = request.getParameter("isMedical");
		String isClaim = request.getParameter("isclaim");
		String claimInformation = request.getParameter("claimInformation");
		String referralName = request.getParameter("referralName");
		
		connection.createClient(passportNumber, clientName, gender, dateOfBirth, mainDiagnosis, clientType, nationality, countryOfResidence, billingStreet, billingCity, billingState, billingCountry, billingCode, isMedical, isClaim, claimInformation, referralName);
		
		
		
		String jsonInString = "{\"status\":\"success\"}";
		out.write(jsonInString);
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
