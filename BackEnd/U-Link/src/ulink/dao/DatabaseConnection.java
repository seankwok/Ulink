package ulink.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ulink.constructor.Client;
import ulink.constructor.Condition;
import ulink.constructor.Consultation;

public class DatabaseConnection {

	public ArrayList<Client> retrieveAllClient(String startDate, String endDate) {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "Select passportNumber,`clientName`,`gender`,dateOfBirth,mainDiagnosis,clientType,nationality,countryOfResidence,billingStreet,billingCity,billingState, billingCountry, billingcode,isMedicial,isClaim,claimInformation, referraldetails.referralName from client inner join referraldetails ON client.referral_ID = referraldetails.referral_ID where referraldetails.r_dateTime >='" + startDate +  "' && referraldetails.r_dateTime <= '" + endDate + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 String passportNumber = rs.getString(1);
				 String clientName = rs.getString(2);
				 String gender = rs.getString(3);
				 String dateOfBirth = rs.getString(4);
				 String mainDianosis = rs.getString(5);
				 String clientType = rs.getString(6);
				 String nationality = rs.getString(7);
				 String countryOfResidence = rs.getString(8);
				 String billingStreet = rs.getString(9);
				 String billingCity = rs.getString(10);
				 String billingState = rs.getString(11);
				 String billingCountry = rs.getString(12);
				 String billingCode = rs.getString(13);
				 String isMedicial = rs.getString(14);
				 String isClaim = rs.getString(15);
				 String claimInformation = rs.getString(16);
				 String referralName = rs.getString(17);
				clientList.add(new Client(passportNumber, clientName, gender, dateOfBirth,
						mainDianosis,clientType, nationality, countryOfResidence, billingStreet,billingCity, billingState, billingCountry, billingCode, isMedicial, isClaim,  claimInformation,referralName));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
	
	public ArrayList<Consultation> retrieveAllConsultation(String startDate, String endDate) {

		Connection con;
		ArrayList<Consultation> consultationList = new ArrayList<Consultation>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "SELECT C_ID, dateTime, consultation.doctorName, clinicName, passportNumber FROM `consultation` INNER JOIN doctor ON consultation.doctorName = doctor.doctorName where dateTime >= '"+ startDate +"' && dateTime <= '" + endDate + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 int ID = rs.getInt(1);
				 String appointment = rs.getString(2);
				 String doctorName = rs.getString(3);
				 String clinicName = rs.getString(4);
				 String passportNumber = rs.getString(5);
				 consultationList.add(new Consultation(ID, appointment, doctorName, clinicName, passportNumber));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return consultationList;
	}
	
	public ArrayList<String> retrieveAllSpeciality() {

		Connection con;
		ArrayList<String> specialityList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "SELECT speciality from doctor INNER join consultation on doctor.doctorName = consultation.doctorName";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 
				 String speciality = rs.getString(1);
				 specialityList.add(speciality);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return specialityList;
	}
	
	public ArrayList<String> retrieveAllTeam(String startDate, String endDate, String team) {

		Connection con;
		ArrayList<String> admissionList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "select client.passportNumber,client.clientName, client.nationality, user.role, followup.hospitalAdmitted from client inner join appointment on client.passportNumber = appointment.passportNumber INNER join user on appointment.email = user.email inner join admission on client.passportNumber = admission.passportNumber inner join followup on admission.followUpID = followup.followUpID where followup.dateOfAdmission >= '"+ startDate +"' && followup.dateOfAdmission <= '"+ endDate +"'&& user.role = '"+ team +"'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 
				 String nationality = rs.getString(3);
				 String hospitalAdmitted = rs.getString(5);
				 admissionList.add(nationality);
				 admissionList.add(hospitalAdmitted);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return admissionList;
	}
	
	
	public ArrayList<Condition> retrieveManCondition() {

		Connection con;
		ArrayList<Condition> manConditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM mancondition";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 
				 String conditionName = rs.getString(1);
				 int numOfYears = rs.getInt(2);
				 Condition condition = new Condition(conditionName,numOfYears);
				 manConditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return manConditionList;
	}
	
	public boolean addManCondition(String conditionName, int numberOfYears) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");

			String sql = "INSERT INTO mancondition (conditionName,numOfYears) "
					+ "VALUES (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.setInt(2, numberOfYears);
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public void deleteManCondition(String conditionName) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");


			String sql = "Delete from mancondition where conditionName = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void editManCondition(String conditionName, int numOfYears) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");

			

			String sql = "UPDATE mancondition SET numOfYears= ? WHERE conditionName= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setInt(1, numOfYears);
			preparedStmt.setString(2, conditionName);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
