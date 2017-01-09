package ulink.dao;

import java.io.PrintWriter;
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
import ulink.constructor.User;
import ulink.logic.Utility;

public class DatabaseConnection {
	
	
	public ArrayList<Client> retrieveAllClientList() {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM client group by clientName";
			ResultSet rs = stmt.executeQuery(sql);
			//Utility utility = new Utility();
			
			while (rs.next()) {
				String accountID = rs.getString(1);
				String clientOwner = rs.getString(2);
				String clientName = rs.getString(3); 
				String clientType = rs.getString(4);
				String company = rs.getString(5);
				String nationality = rs.getString(6);
				String gender = rs.getString(7);
				String dateOfBirth = rs.getString(8);
				String email = rs.getString(9);
				String medical = rs.getString(10);
				String mainDiagnosis = rs.getString(11);
				String referredBy = rs.getString(12);
				String PIC = rs.getString(13);
				String appointment = rs.getString(14);
				String doctor = rs.getString(15);
				String specialty = rs.getString(16);
				String clinic = rs.getString(17);
				String otherDoctor = rs.getString(18);
				String followUpPerson = rs.getString(19);
				String followUpPIC = rs.getString(20);
				String hospitalAdmitted = rs.getString(21);
				String log = rs.getString(22);
				String claim = rs.getString(23);
				String visaRequestBy = rs.getString(24);
				String visa = rs.getString(25);
				String visaType = rs.getString(26);
				String visaType2 = rs.getString(27);
				
				clientList.add(new Client(accountID,clientOwner,clientName,clientType,company,nationality,gender,dateOfBirth,email,medical,
						mainDiagnosis,referredBy,PIC,appointment,doctor,specialty,clinic,otherDoctor,followUpPerson,followUpPIC,hospitalAdmitted,
						log,claim, visaRequestBy,visa,visaType,visaType2));
			}
				
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
	
	public ArrayList<Client> retrieveAllClientByName(String name) {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select * from client where clientName='"+ name +"'";
			ResultSet rs = stmt.executeQuery(sql);
			//Utility utility = new Utility();
			
			while (rs.next()) {
				String accountID = rs.getString(1);
				String clientOwner = rs.getString(2);
				String clientName = rs.getString(3); 
				String clientType = rs.getString(4);
				String company = rs.getString(5);
				String nationality = rs.getString(6);
				String gender = rs.getString(7);
				String dateOfBirth = rs.getString(8);
				String email = rs.getString(9);
				String medical = rs.getString(10);
				String mainDiagnosis = rs.getString(11);
				String referredBy = rs.getString(12);
				String PIC = rs.getString(13);
				String appointment = rs.getString(14);
				String doctor = rs.getString(15);
				String specialty = rs.getString(16);
				String clinic = rs.getString(17);
				String otherDoctor = rs.getString(18);
				String followUpPerson = rs.getString(19);
				String followUpPIC = rs.getString(20);
				String hospitalAdmitted = rs.getString(21);
				String log = rs.getString(22);
				String claim = rs.getString(23);
				String visaRequestBy = rs.getString(24);
				String visa = rs.getString(25);
				String visaType = rs.getString(26);
				String visaType2 = rs.getString(27);
				
				clientList.add(new Client(accountID,clientOwner,clientName,clientType,company,nationality,gender,dateOfBirth,email,medical,
						mainDiagnosis,referredBy,PIC,appointment,doctor,specialty,clinic,otherDoctor,followUpPerson,followUpPIC,hospitalAdmitted,
						log,claim, visaRequestBy,visa,visaType,visaType2));
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
			String sql = "Select passportNumber,`clientName`,`gender`,dateOfBirth,mainDiagnosis,clientType,nationality,countryOfResidence,billingStreet,billingCity,billingState, billingCountry, billingcode,ismedical,isClaim,claimInformation, referraldetails.referralName from client inner join referraldetails ON client.referral_ID = referraldetails.referral_ID where referraldetails.r_dateTime >='"
					+ startDate + "' && referraldetails.r_dateTime <= '" + endDate + "'" ;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String accountID = rs.getString(1);
				String clientOwner = rs.getString(2);
				String clientName = rs.getString(3); 
				String clientType = rs.getString(4);
				String company = rs.getString(5);
				String nationality = rs.getString(6);
				String gender = rs.getString(7);
				String dateOfBirth = rs.getString(8);
				String email = rs.getString(9);
				String medical = rs.getString(10);
				String mainDiagnosis = rs.getString(11);
				String referredBy = rs.getString(12);
				String PIC = rs.getString(13);
				String appointment = rs.getString(14);
				String doctor = rs.getString(15);
				String specialty = rs.getString(16);
				String clinic = rs.getString(17);
				String otherDoctor = rs.getString(18);
				String followUpPerson = rs.getString(19);
				String followUpPIC = rs.getString(20);
				String hospitalAdmitted = rs.getString(21);
				String log = rs.getString(22);
				String claim = rs.getString(23);
				String visaRequestBy = rs.getString(24);
				String visa = rs.getString(25);
				String visaType = rs.getString(26);
				String visaType2 = rs.getString(27);
				
				clientList.add(new Client(accountID,clientOwner,clientName,clientType,company,nationality,gender,dateOfBirth,email,medical,
						mainDiagnosis,referredBy,PIC,appointment,doctor,specialty,clinic,otherDoctor,followUpPerson,followUpPIC,hospitalAdmitted,
						log,claim, visaRequestBy,visa,visaType,visaType2));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
	
	public void createClient(String accountID, String clientOwner, String clientName, String clientType, String company,
			String nationality, String gender, String dateOfBirth, String email, String medical, String mainDiagnosis,
			String referredBy, String PIC, String appointment, String doctor, String specialty, String clinic,
			String otherDoctor, String followUpPerson, String followUpPIC, String hospitalAdmitted, String log,
			String claim,String visaRequestBy, String visa, String visaType, String visaType2) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO client (accountID,clientOwner,clientName,clientType,company,nationality,gender,dateOfBirth,email,medical,mainDiagnosis,referredBy,PIC,appointment,doctor,specialty,clinic,otherDoctor,followUpPerson,followUpPIC,hospitalAdmitted,log,claim, visaRequestBy,visa,visaType,visaType2)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Utility utility = new Utility();
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, accountID);
			preparedStmt.setString(2, clientOwner);
			preparedStmt.setString(3, clientName);
			preparedStmt.setString(4, clientType);
			preparedStmt.setString(5, company);
			preparedStmt.setString(6, nationality);
			preparedStmt.setString(7, gender);
			preparedStmt.setString(8, dateOfBirth);
			preparedStmt.setString(9, email);
			preparedStmt.setString(10, medical);
			preparedStmt.setString(11, mainDiagnosis);
			preparedStmt.setString(12, referredBy);
			preparedStmt.setString(13, PIC);
			preparedStmt.setString(14, appointment);
			preparedStmt.setString(15, doctor);
			preparedStmt.setString(16, specialty);
			preparedStmt.setString(17, clinic);
			preparedStmt.setString(18, otherDoctor);
			preparedStmt.setString(19, followUpPerson);
			preparedStmt.setString(20, followUpPIC);
			preparedStmt.setString(21, hospitalAdmitted);
			preparedStmt.setString(22, log);
			preparedStmt.setString(23, claim);
			preparedStmt.setString(24, visaRequestBy);
			preparedStmt.setString(25, visa);
			preparedStmt.setString(26, visaType);
			preparedStmt.setString(27, visaType2);
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			
		}

	}
	

	

	public ArrayList<Consultation> retrieveAllConsultation(String startDate, String endDate) {

		Connection con;
		ArrayList<Consultation> consultationList = new ArrayList<Consultation>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT C_ID, dateTime, consultation.doctorName, clinicName, passportNumber FROM `consultation` INNER JOIN doctor ON consultation.doctorName = doctor.doctorName where dateTime >= '"
					+ startDate + "' && dateTime <= '" + endDate +  "' && doctor.doctorName != 'null'";

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

	public ArrayList<String> retrieveAllSpeciality(String startDate, String endDate) {

		Connection con;
		ArrayList<String> specialityList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT speciality from doctor INNER join consultation on doctor.doctorName = consultation.doctorName where dateTime >= '"
					+ startDate + "' && dateTime <= '" + endDate + "' && doctor.doctorName != 'null'";

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
					+ startDate + "' && followup.dateOfAdmission <= '" + endDate  + "' && user.role = '" + team  + "' group BY client.passportNumber, followup.hospitalAdmitted";

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
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				Condition condition = new Condition(ID,conditionName, numOfYears, ageRequired, screening, type);
				allconditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allconditionList;
	}

	public boolean addAllCondition(String conditionName, String numberOfYears, int ageRequired, String screening, String type) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO allcondition (conditionName,numOfYears,ageRequired, screening, type)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.setString(2, numberOfYears);
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

	public void deleteAllCondition(int ID) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "Delete from allcondition where ID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setInt(1, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editAllCondition(int ID, String conditionName, String numOfYears, int ageRequired, String screening, String type) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "UPDATE allcondition SET numOfYears= ?, ageRequired = ?, screening = ?, type = ?, conditionName = ?  WHERE ID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, numOfYears);
			preparedStmt.setInt(2, ageRequired);
			preparedStmt.setString(3, screening);
			preparedStmt.setString(4, type);
			preparedStmt.setString(5, conditionName);
			preparedStmt.setInt(6, ID);
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
			String sql = "SELECT * FROM allcondition where type= 'both' || type='" + types + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				Condition condition = new Condition(ID, conditionName, numOfYears, ageRequired, screening, type);
				allconditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allconditionList;
	}
	
	public Condition retrieveConditionDetails(int ID) {
		Condition condition = null;
		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where ID='" + ID + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//int Id = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				condition = new Condition(ID,conditionName, numOfYears, ageRequired, screening, type);
			
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return condition;
	}
	

	public void createUser(String email, String password, String roles){
		Connection con;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");
		Statement stmt = con.createStatement();

		String sql = "INSERT INTO user (email,password,roles) "
				+ "VALUES (?,?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		preparedStmt.setString(1, email);
		preparedStmt.setString(2, password);
		preparedStmt.setString(3, roles);
		preparedStmt.execute();

		con.close();

	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	public ArrayList<User> getUser(){
		ArrayList<User> userList = new ArrayList<User>();
		Connection con;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");
		Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from User");
			while (rs.next()) {
				String email = rs.getString(1);
				String password = rs.getString(2);
				String roles = rs.getString(3);	
				userList.add(new User(email, password,roles));
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}
	
	public void deleteUser(String email) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "DELETE FROM user WHERE email = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, email);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void editUser(String email, String password) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			String sql = "UPDATE user SET password= ? WHERE email = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, password);
			preparedStmt.setString(2, email);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getUserByEmail(String email){
		Connection con;
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ulink", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM User where email='" + email + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//int Id = rs.getInt(1);
				String emailID = rs.getString(1);
				String password = rs.getString(2);
				String roles = rs.getString(3);
				//String screening = rs.getString(5);
				//String type = rs.getString(6);
				user = new User(emailID, password, roles);
			
			}

			con.close();
			

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}

}

