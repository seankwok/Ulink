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
import ulink.constructor.Timeline;

public class DatabaseConnection {
	
	
	public ArrayList<Client> retrieveAllClientList() {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "Select passportNumber,`clientName`,`gender`,dateOfBirth,mainDiagnosis,clientType,nationality,countryOfResidence,billingStreet,billingCity,billingState, billingCountry, billingcode,isMedicial,isClaim,claimInformation, referraldetails.referralName from client inner join referraldetails ON client.referral_ID = referraldetails.referral_ID";
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
				clientList.add(new Client(passportNumber, clientName, gender, dateOfBirth, mainDianosis, clientType,
						nationality, countryOfResidence, billingStreet, billingCity, billingState, billingCountry,
						billingCode, isMedicial, isClaim, claimInformation, referralName));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
	
	public ArrayList<Client> retrieveAllClient(String startDate, String endDate) {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "Select passportNumber,`clientName`,`gender`,dateOfBirth,mainDiagnosis,clientType,nationality,countryOfResidence,billingStreet,billingCity,billingState, billingCountry, billingcode,isMedicial,isClaim,claimInformation, referraldetails.referralName from client inner join referraldetails ON client.referral_ID = referraldetails.referral_ID where referraldetails.r_dateTime >='"
					+ startDate + "' && referraldetails.r_dateTime <= '" + endDate + "'";
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
				clientList.add(new Client(passportNumber, clientName, gender, dateOfBirth, mainDianosis, clientType,
						nationality, countryOfResidence, billingStreet, billingCity, billingState, billingCountry,
						billingCode, isMedicial, isClaim, claimInformation, referralName));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT C_ID, dateTime, consultation.doctorName, clinicName, passportNumber FROM `consultation` INNER JOIN doctor ON consultation.doctorName = doctor.doctorName where dateTime >= '"
					+ startDate + "' && dateTime <= '" + endDate + "'";

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select client.passportNumber,client.clientName, client.nationality, user.role, followup.hospitalAdmitted from client inner join appointment on client.passportNumber = appointment.passportNumber INNER join user on appointment.email = user.email inner join admission on client.passportNumber = admission.passportNumber inner join followup on admission.followUpID = followup.followUpID where followup.dateOfAdmission >= '"
					+ startDate + "' && followup.dateOfAdmission <= '" + endDate + "'&& user.role = '" + team + "'";

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

	public ArrayList<Condition> retrieveAllCondition() {

		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String conditionName = rs.getString(1);
				int numOfYears = rs.getInt(2);
				int ageRequired = rs.getInt(3);
				String screening = rs.getString(4);
				String type = rs.getString(5);
				Condition condition = new Condition(conditionName, numOfYears, ageRequired, screening, type);
				allconditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allconditionList;
	}

	public boolean addAllCondition(String conditionName, int numberOfYears, int ageRequired, String screening, String type) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO allcondition (conditionName,numOfYears,ageRequired, screening, type)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.setInt(2, numberOfYears);
			preparedStmt.setInt(3, ageRequired);
			preparedStmt.setString(4, screening);
			preparedStmt.setString(5, type);
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void deleteAllCondition(String conditionName) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "Delete from allcondition where conditionName = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editAllCondition(String conditionName, int numOfYears, int ageRequired, String screening, String type) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "UPDATE allcondition SET numOfYears= ? ageRequired = ? screening = ? type = ? WHERE conditionName= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setInt(1, numOfYears);
			preparedStmt.setInt(2, ageRequired);
			preparedStmt.setString(3, screening);
			preparedStmt.setString(4, type);
			preparedStmt.setString(5, conditionName);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Timeline> getTimeline() {
		Connection con;
		ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "Select * from listoftimeline";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String passportNumber = rs.getString(3);
				int yearToSend = rs.getInt(4);
				Timeline timeline = new Timeline(ID,conditionName,passportNumber,yearToSend);
				timelineList.add(timeline);
			}
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timelineList;
	}
	
	
	public void editTimeline(int ID, int yearToSend) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "UPDATE listoftimeline SET yearsToSend= ? WHERE timelineID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setInt(1, yearToSend);
			preparedStmt.setInt(2, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addTimeline(String conditionName, String passportNumber, int yearToSend) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO listoftimeline (conditionName,passportNumber,yearsToSend) "
					+ "VALUES (?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.setString(2,passportNumber);
			preparedStmt.setInt(3, yearToSend);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<Condition> retrieveAllTypeCondition(String types) {

		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where type='" + types + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String conditionName = rs.getString(1);
				int numOfYears = rs.getInt(2);
				int ageRequired = rs.getInt(3);
				String screening = rs.getString(4);
				String type = rs.getString(5);
				Condition condition = new Condition(conditionName, numOfYears, ageRequired, screening, type);
				allconditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allconditionList;
	}

}
