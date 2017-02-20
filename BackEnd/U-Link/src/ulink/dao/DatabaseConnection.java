package ulink.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ulink.constructor.*;
import ulink.logic.Utility;

public class DatabaseConnection {

	public ArrayList<String> retrieveAllEmail() {
		Connection con;
		ArrayList<String> templateList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT templateName FROM emailtemplate";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String templateName = rs.getString(1);
				templateList.add(templateName);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return templateList;

	}

	public ArrayList<Index> retrieveAllIndex(String startDate, String endDate, String medicial) {
		Connection con;
		ArrayList<Index> IndexList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select billingCity, BillingCode, BillingCountry, BillingState, BillingStreet,phone,email from client where `CreatedTime` between'"
					+ startDate + "'and'" + endDate + "' and medical = '" + medicial + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String billing = rs.getString(1);
				billing += rs.getString(2);
				billing += rs.getString(3);
				billing += rs.getString(4);
				billing += rs.getString(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				IndexList.add(new Index(billing, phone, email));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return IndexList;

	}

	
	public ArrayList<String> retrieveAllPersonInCharge(){
		Connection con;
		ArrayList<String> followUpPersonList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select followUpPerson from client where followUpPerson != '' group by followUpPerson";
			
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String followUpPerson = rs.getString(1);
				followUpPersonList.add(followUpPerson);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return followUpPersonList;

	}
	
	public ArrayList<Index> retrieveAllIndexByPerson(String startDate, String endDate, String medicial, String followUpPerson) {
		Connection con;
		ArrayList<Index> IndexList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select ID,billingCity, BillingCode, BillingCountry, BillingState, BillingStreet,phone,email from client where `CreatedTime` between'"+startDate+"'and'"+endDate+"' and medical = '"+medicial+"' and `followUpPerson`= '"+followUpPerson+"'";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String billing = rs.getString(1);
				billing += rs.getString(2);
				billing += rs.getString(3);
				billing += rs.getString(4);
				billing += rs.getString(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				IndexList.add(new Index(billing, phone, email));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return IndexList;

	}
	
	
	public ArrayList<RankingReferredBy> retrieveAllRankingReferredByDashBoard(String startDate, String endDate) {
		Connection con;
		ArrayList<RankingReferredBy> referredByList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select  referredBy, count(referredBy) from client where `CreatedTime` between'" + startDate
					+ "'and'" + endDate + "' and referredBy != '' group by referredBy ORDER BY COUNT(referredBy) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();
			int ranking = 0;
			int previous = 0;
			while (rs.next()) {
				String referredBy = rs.getString(1);
				int count = rs.getInt(2);

				if (previous == count) {
					referredByList.add(new RankingReferredBy(ranking, referredBy, count));

				} else {
					if (ranking < 3) {
						ranking++;
						referredByList.add(new RankingReferredBy(ranking, referredBy, count));
						previous = count;
					}

				}

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return referredByList;

	}

	public ArrayList<RankingDoctor> retrieveAllRankingDoctorDashBoard(String startDate, String endDate) {
		Connection con;
		ArrayList<RankingDoctor> doctorList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			// String sql = "select doctor, clinic, specialty,
			// count(appointment) from client where CreatedTime BETWEEN
			// ('"+startDate+"' - INTERVAL 1 MONTH) AND '"+endDate+"' and doctor
			// != '' group by doctor ORDER BY COUNT(appointment) DESC ";
			String sql = "select doctor, clinic, specialty, count(appointment) from client where `CreatedTime` between'"
					+ startDate + "'and'" + endDate
					+ "' and doctor != '' group by doctor ORDER BY COUNT(appointment) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			// Utility utility = new Utility();
			int ranking = 0;
			int previous = 0;
			while (rs.next()) {
				String doctor = rs.getString(1);
				String clinic = rs.getString(2);
				String specialty = rs.getString(3);
				int count = rs.getInt(4);

				if (previous == count) {
					doctorList.add(new RankingDoctor(ranking, doctor, clinic, specialty, count));

				} else {
					if (ranking < 5) {
						ranking++;
						doctorList.add(new RankingDoctor(ranking, doctor, clinic, specialty, count));
						previous = count;

					}
				}

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doctorList;

	}

	public String retrieveLatestDate() {

		Connection con;
		String createTime = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT CreatedTime FROM `client` order BY(CreatedTime) DESC limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				createTime = rs.getString(1);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return createTime;

	}

	public ArrayList<String> retrievePastSixMonthRecord(String type, String startDate, String endDate) {
		Connection con;
		ArrayList<String> dateList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT CreatedTime FROM `client` WHERE CreatedTime BETWEEN ('" + startDate
					+ "' - INTERVAL 5 MONTH) AND '" + endDate + "' and medical = '" + type + "'order by createdTime";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String createTime = rs.getString(1);
				dateList.add(createTime);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateList;

	}

	// select referredBy, count(referredBy) from client where `CreatedTime`
	// between '2011/02/25' and '2011/02/27' group by referredBy

	public ArrayList<RankingReferredBy> retrieveAllRankingReferredBy(String startDate, String endDate) {
		Connection con;
		ArrayList<RankingReferredBy> referredByList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select  referredBy, count(referredBy) from client where `CreatedTime` between'" + startDate
					+ "'and'" + endDate + "' and referredBy != '' group by referredBy ORDER BY COUNT(referredBy) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String referredBy = rs.getString(1);
				int count = rs.getInt(2);
				referredByList.add(new RankingReferredBy(0, referredBy, count));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return referredByList;

	}

	public ArrayList<RankingSpecialty> retrieveAllRankingSpecialty(String startDate, String endDate) {
		Connection con;
		ArrayList<RankingSpecialty> specialtyList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select specialty, count(appointment) from client where `CreatedTime` between'" + startDate
					+ "'and'" + endDate + "' and specialty != '' group by specialty ORDER BY COUNT(appointment) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			//Utility utility = new Utility();

			while (rs.next()) {
				String specialty = rs.getString(1);
				int count = rs.getInt(2);
				specialtyList.add(new RankingSpecialty(specialty, count));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return specialtyList;

	}
	
	public String getNameByEmail(String email) {
		Connection con;
		String name = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select clientName from client where email = '" + email + "'"; 
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				name = rs.getString(1);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;

	}

	public ArrayList<RankingDoctor> retrieveAllRankingDoctor(String startDate, String endDate) {
		Connection con;
		ArrayList<RankingDoctor> doctorList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select doctor, clinic, specialty, count(appointment) from client where `CreatedTime` between'"
					+ startDate + "'and'" + endDate
					+ "' and doctor != '' group by doctor ORDER BY COUNT(appointment) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String doctor = rs.getString(1);
				String clinic = rs.getString(2);
				String specialty = rs.getString(3);
				int count = rs.getInt(4);
				doctorList.add(new RankingDoctor(0, doctor, clinic, specialty, count));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doctorList;

	}

	public ArrayList<RankingDoctorSpecialty> retrieveAllDoctorBySpecialty(String specialty, String startDate,
			String endDate) {
		Connection con;
		ArrayList<RankingDoctorSpecialty> doctorList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select doctor, count(appointment) from client where `CreatedTime` between'" + startDate
					+ "'and'" + endDate + "' and specialty= '" + specialty
					+ "' group by doctor ORDER BY COUNT(appointment) DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				String doctor = rs.getString(1);
				int count = rs.getInt(2);
				doctorList.add(new RankingDoctorSpecialty(doctor, count));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doctorList;

	}
	
	public ArrayList<Client> retrieveAllClientListVisa() {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM client";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				int ID = rs.getInt(1);
				String accountID = rs.getString(2);
				String clientOwner = rs.getString(3);
				String clientName = rs.getString(4);
				String clientType = rs.getString(5);
				String company = rs.getString(6);
				String nationality = rs.getString(7);
				String gender = rs.getString(8);
				String dateOfBirth = rs.getString(9);
				String email = rs.getString(10);
				String medical = rs.getString(11);
				String mainDiagnosis = rs.getString(12);
				String referredBy = rs.getString(13);
				String PIC = rs.getString(14);
				String appointment = rs.getString(15);
				String doctor = rs.getString(16);
				String specialty = rs.getString(17);
				String clinic = rs.getString(18);
				String otherDoctor = rs.getString(19);
				String followUpPerson = rs.getString(20);
				String followUpPIC = rs.getString(21);
				String hospitalAdmitted = rs.getString(22);
				String log = rs.getString(23);
				String claim = rs.getString(24);
				String visaRequestBy = rs.getString(25);
				String visa = rs.getString(26);
				String visaType = rs.getString(27).trim();
				String visaType2 = rs.getString(28);
				int age = rs.getInt(29);
				String billingCity = rs.getString(30);
				String billingCode = rs.getString(31);
				String billingCountry = rs.getString(32);
				String billingState = rs.getString(33);
				String billingStreet = rs.getString(34);
				String createdTime = "" + rs.getDate(35);
				String phone = rs.getString(36);

				clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
						gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
						specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2, age, billingCity, billingCode, billingCountry,
						billingState, billingStreet, createdTime, phone));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}

	
	
	
	
	public ArrayList<Client> retrieveAllClientList() {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM client group by clientName";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				int ID = rs.getInt(1);
				String accountID = rs.getString(2);
				String clientOwner = rs.getString(3);
				String clientName = rs.getString(4);
				String clientType = rs.getString(5);
				String company = rs.getString(6);
				String nationality = rs.getString(7);
				String gender = rs.getString(8);
				String dateOfBirth = rs.getString(9);
				String email = rs.getString(10);
				String medical = rs.getString(11);
				String mainDiagnosis = rs.getString(12);
				String referredBy = rs.getString(13);
				String PIC = rs.getString(14);
				String appointment = rs.getString(15);
				String doctor = rs.getString(16);
				String specialty = rs.getString(17);
				String clinic = rs.getString(18);
				String otherDoctor = rs.getString(19);
				String followUpPerson = rs.getString(20);
				String followUpPIC = rs.getString(21);
				String hospitalAdmitted = rs.getString(22);
				String log = rs.getString(23);
				String claim = rs.getString(24);
				String visaRequestBy = rs.getString(25);
				String visa = rs.getString(26);
				String visaType = rs.getString(27).trim();
				String visaType2 = rs.getString(28);
				int age = rs.getInt(29);
				String billingCity = rs.getString(30);
				String billingCode = rs.getString(31);
				String billingCountry = rs.getString(32);
				String billingState = rs.getString(33);
				String billingStreet = rs.getString(34);
				String createdTime = "" + rs.getDate(35);
				String phone = rs.getString(36);

				clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
						gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
						specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2, age, billingCity, billingCode, billingCountry,
						billingState, billingStreet, createdTime, phone));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}

	public ArrayList<Client> retrieveAllClientListByOrder(String type, String type2) {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM client where clientType= '" + type + "' and medical= '" + type2 + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				int ID = rs.getInt(1);
				String accountID = rs.getString(2);
				String clientOwner = rs.getString(3);
				String clientName = rs.getString(4);
				String clientType = rs.getString(5);
				String company = rs.getString(6);
				String nationality = rs.getString(7);
				String gender = rs.getString(8);
				String dateOfBirth = rs.getString(9);
				String email = rs.getString(10);
				String medical = rs.getString(11);
				String mainDiagnosis = rs.getString(12);
				String referredBy = rs.getString(13);
				String PIC = rs.getString(14);
				String appointment = rs.getString(15);
				String doctor = rs.getString(16);
				String specialty = rs.getString(17);
				String clinic = rs.getString(18);
				String otherDoctor = rs.getString(19);
				String followUpPerson = rs.getString(20);
				String followUpPIC = rs.getString(21);
				String hospitalAdmitted = rs.getString(22);
				String log = rs.getString(23);
				String claim = rs.getString(24);
				String visaRequestBy = rs.getString(25);
				String visa = rs.getString(26);
				String visaType = rs.getString(27);
				String visaType2 = rs.getString(28);
				int age = rs.getInt(29);
				String billingCity = rs.getString(30);
				String billingCode = rs.getString(31);
				String billingCountry = rs.getString(32);
				String billingState = rs.getString(33);
				String billingStreet = rs.getString(34);
				String createdTime = "" + rs.getDate(35);
				String phone = rs.getString(36);

				clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
						gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
						specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2, age, billingCity, billingCode, billingCountry,
						billingState, billingStreet, createdTime, phone));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select * from client where clientName='" + name + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				int ID = rs.getInt(1);
				String accountID = rs.getString(2);
				String clientOwner = rs.getString(3);
				String clientName = rs.getString(4);
				String clientType = rs.getString(5);
				String company = rs.getString(6);
				String nationality = rs.getString(7);
				String gender = rs.getString(8);
				String dateOfBirth = rs.getString(9);
				String email = rs.getString(10);
				String medical = rs.getString(11);
				String mainDiagnosis = rs.getString(12);
				String referredBy = rs.getString(13);
				String PIC = rs.getString(14);
				String appointment = rs.getString(15);
				String doctor = rs.getString(16);
				String specialty = rs.getString(17);
				String clinic = rs.getString(18);
				String otherDoctor = rs.getString(19);
				String followUpPerson = rs.getString(20);
				String followUpPIC = rs.getString(21);
				String hospitalAdmitted = rs.getString(22);
				String log = rs.getString(23);
				String claim = rs.getString(24);
				String visaRequestBy = rs.getString(25);
				String visa = rs.getString(26);
				String visaType = rs.getString(27);
				String visaType2 = rs.getString(28);
				int age = rs.getInt(29);
				String billingCity = rs.getString(30);
				String billingCode = rs.getString(31);
				String billingCountry = rs.getString(32);
				String billingState = rs.getString(33);
				String billingStreet = rs.getString(34);
				String createdTime = "" + rs.getDate(35);
				String phone = rs.getString(36);
				clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
						gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
						specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2, age, billingCity, billingCode, billingCountry,
						billingState, billingStreet, createdTime, phone));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
	
	
	public ArrayList<Client> retrieveAllClientByType(String type, String startDate, String endDate) {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select * from client where  `CreatedTime` between'" + startDate + "'and'" + endDate
					+ "'and medical='" + type + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Utility utility = new Utility();

			while (rs.next()) {
				int ID = rs.getInt(1);
				String accountID = rs.getString(2);
				String clientOwner = rs.getString(3);
				String clientName = rs.getString(4);
				String clientType = rs.getString(5);
				String company = rs.getString(6);
				String nationality = rs.getString(7);
				String gender = rs.getString(8);
				String dateOfBirth = rs.getString(9);
				String email = rs.getString(10);
				String medical = rs.getString(11);
				String mainDiagnosis = rs.getString(12);
				String referredBy = rs.getString(13);
				String PIC = rs.getString(14);
				String appointment = rs.getString(15);
				String doctor = rs.getString(16);
				String specialty = rs.getString(17);
				String clinic = rs.getString(18);
				String otherDoctor = rs.getString(19);
				String followUpPerson = rs.getString(20);
				String followUpPIC = rs.getString(21);
				String hospitalAdmitted = rs.getString(22);
				String log = rs.getString(23);
				String claim = rs.getString(24);
				String visaRequestBy = rs.getString(25);
				String visa = rs.getString(26);
				String visaType = rs.getString(27);
				String visaType2 = rs.getString(28);
				int age = rs.getInt(29);
				String billingCity = rs.getString(30);
				String billingCode = rs.getString(31);
				String billingCountry = rs.getString(32);
				String billingState = rs.getString(33);
				String billingStreet = rs.getString(34);
				String createdTime = "" + rs.getDate(35);
				String phone = rs.getString(36);
				clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
						gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
						specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2, age, billingCity, billingCode, billingCountry,
						billingState, billingStreet, createdTime, phone));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}

	public void createClient(ArrayList<Client> clientList) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO client (accountID,clientOwner,clientName,clientType,company,nationality,gender,dateOfBirth,email,medical,"
					+ "mainDiagnosis,referredBy,PIC,appointment,doctor,specialty,clinic,otherDoctor,followUpPerson,followUpPIC,hospitalAdmitted,"
					+ "log,claim, visaRequestBy,visa,visaType,visaType2, age,billingCity,billingCode,billingCountry,billingState,billingStreet,createdTime,phone)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Utility utility = new Utility();
			PreparedStatement preparedStmt = con.prepareStatement(sql);

			for (int i = 1; i < clientList.size(); i++) {
				Client client = clientList.get(i);

				// preparedStmt.setInt(1, client.getID());
				preparedStmt.setString(1, client.getAccountID());
				preparedStmt.setString(2, client.getClientOwner());
				preparedStmt.setString(3, client.getClientName());
				preparedStmt.setString(4, client.getClientType());
				preparedStmt.setString(5, client.getCompany());
				preparedStmt.setString(6, client.getNationality());
				preparedStmt.setString(7, client.getGender());
				preparedStmt.setString(8, client.getDateOfBirth());
				preparedStmt.setString(9, client.getEmail());
				if (client.getMedical().equals("false") && client.getVisa().equals("false")) {
					preparedStmt.setString(10, "");
				} else {
					if (client.getMedical().equals("true")) {
						preparedStmt.setString(10, "Medical");
					} else {
						preparedStmt.setString(10, "Visa");
					}
				}
				preparedStmt.setString(11, client.getMainDiagnosis());
				preparedStmt.setString(12, client.getReferredBy());
				preparedStmt.setString(13, client.getPIC());
				preparedStmt.setString(14, client.getAppointment());
				preparedStmt.setString(15, client.getDoctor());
				preparedStmt.setString(16, client.getSpecialty());
				preparedStmt.setString(17, client.getClinic());
				preparedStmt.setString(18, client.getOtherDoctor());
				preparedStmt.setString(19, client.getFollowUpPerson());
				preparedStmt.setString(20, client.getFollowUpPIC());
				preparedStmt.setString(21, client.getHospitalAdmitted());
				preparedStmt.setString(22, client.getLog());
				preparedStmt.setString(23, client.getClaim());
				preparedStmt.setString(24, client.getVisaRequestBy());
				preparedStmt.setString(25, client.getVisa());
				preparedStmt.setString(26, client.getVisaType());
				preparedStmt.setString(27, client.getVisaType2());
				preparedStmt.setInt(28, client.getAge());
				preparedStmt.setString(29, client.getBillingCity());
				preparedStmt.setString(30, client.getBillingCode());
				preparedStmt.setString(31, client.getBillingCountry());
				preparedStmt.setString(32, client.getBillingState());
				preparedStmt.setString(33, client.getBillingStreet());
				// System.out.println(client.getBillingStreet());
				try {
					Integer.parseInt(client.getCreatedtime().substring(0, 2));
				} catch (NumberFormatException e) {
					client.setCreatedtime("0" + client.getCreatedtime());
				}
				// System.out.print(client.getCreatedtime());
				String date = client.getCreatedtime().substring(6, 10) + "-" + client.getCreatedtime().substring(3, 5)
						+ "-" + client.getCreatedtime().substring(0, 2);
				System.out.println(date);
				preparedStmt.setDate(34, java.sql.Date.valueOf(date));
				preparedStmt.setString(35, client.getPhone());
				preparedStmt.execute();
				// preparedStmt.addBatch();

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	// SELECT * FROM `allcondition` ORDER BY conditionName DESC

	public ArrayList<Consultation> retrieveAllConsultation(String startDate, String endDate) {

		Connection con;
		ArrayList<Consultation> consultationList = new ArrayList<Consultation>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT C_ID, dateTime, consultation.doctorName, clinicName, passportNumber FROM `consultation` INNER JOIN doctor ON consultation.doctorName = doctor.doctorName where dateTime >= '"
					+ startDate + "' && dateTime <= '" + endDate + "' && doctor.doctorName != 'null'";

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "select client.passportNumber,client.clientName, client.nationality, user.role, followup.hospitalAdmitted from client inner join appointment on client.passportNumber = appointment.passportNumber INNER join user on appointment.email = user.email inner join admission on client.passportNumber = admission.passportNumber inner join followup on admission.followUpID = followup.followUpID where followup.dateOfAdmission >= '"
					+ startDate + "' && followup.dateOfAdmission <= '" + endDate + "' && user.role = '" + team
					+ "' group BY client.passportNumber, followup.hospitalAdmitted";

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where type != 'infant'";

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

	public Condition retrieveAllConditionByID(int cID) {

		Connection con;
		// ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		Condition condition = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where ID = '" + cID + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				condition = new Condition(ID, conditionName, numOfYears, ageRequired, screening, type);
				// allconditionList.add(condition);
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return condition;
	}

	public ArrayList<Condition> retrieveAllConditionBySort(String name) {

		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM `allcondition` where type ='" + name + "'";

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

	public ArrayList<Condition> retrieveAllConditionInfant() {

		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where type = 'infant'";

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

	public String getDateTime() {

		Connection con;
		String dateTime = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT LAST(dateTime) FROM uploadtime";
			ResultSet rs = stmt.executeQuery(sql);

			dateTime = rs.getString(2);

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		return dateTime;

	}

	public void addDateTime(String datetime) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO uploadtime (dateTime)" + "VALUES (?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, datetime);
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public boolean addAllCondition(String conditionName, String numberOfYears, int ageRequired, String screening,
			String type) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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

	public void editAllCondition(int ID, String conditionName, String numOfYears, int ageRequired, String screening,
			String type) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			String sql = "Select * from listoftimeline";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String passportNumber = rs.getString(3);
				int yearToSend = rs.getInt(4);
				Timeline timeline = new Timeline(ID, conditionName, passportNumber, yearToSend);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			String sql = "INSERT INTO listoftimeline (conditionName,passportNumber,yearsToSend) " + "VALUES (?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, conditionName);
			preparedStmt.setString(2, passportNumber);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where ID='" + ID + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// int Id = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				condition = new Condition(ID, conditionName, numOfYears, ageRequired, screening, type);

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return condition;
	}

	public ArrayList<Condition> retrieveConditionListByAge(int year) {
		Condition condition = null;
		Connection con;
		ArrayList<Condition> allconditionList = new ArrayList<Condition>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM allcondition where ageRequired <'" + year + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int ID = rs.getInt(1);
				String conditionName = rs.getString(2);
				String numOfYears = rs.getString(3);
				int ageRequired = rs.getInt(4);
				String screening = rs.getString(5);
				String type = rs.getString(6);
				allconditionList.add(new Condition(ID, conditionName, numOfYears, ageRequired, screening, type));

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allconditionList;
	}

	public void createUser(String email, String password, String roles) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			Statement stmt = con.createStatement();

			String sql = "INSERT INTO user (email,password,roles) " + "VALUES (?,?,?)";
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

	
	public ArrayList<EmailSend> retrieveEmailSendDetails(String clientName) {
		//EmailSend emailSend = null;
		Connection con;
		ArrayList<EmailSend> allEmailSendList = new ArrayList<EmailSend>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM emailsend where clientName='" + clientName + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// int Id = rs.getInt(1);
				//String clientName = rs.getString(2);
				String screening = rs.getString(3);
				String date = rs.getString(4);
				//String screening = rs.getString(5);
				//String type = rs.getString(6);
				allEmailSendList.add(new EmailSend(clientName, screening, date));

			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allEmailSendList;
	}

	
	public void createEmailDate(String clientName, String screening, String date) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			Statement stmt = con.createStatement();

			String sql = "INSERT INTO emailsend (clientName,screening,date) " + "VALUES (?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, clientName);
			preparedStmt.setString(2, screening);
			preparedStmt.setString(3, date);
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void createEmailTemplate(String name, String details) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			Statement stmt = con.createStatement();

			String sql = "INSERT INTO emailtemplate (templateName,msg) " + "VALUES (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, details);

			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String retrieveEmailTemplate(String name) {
		Connection con;
		String msg = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select msg from emailtemplate where templateName='" + name + "'");
			while (rs.next()) {
				msg = rs.getString(1);

			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;

	}

	public void deleteEmailTemplate(String templateName) {
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			String sql = "DELETE FROM emailtemplate WHERE templateName = ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, templateName);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<User> getUser() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
			// "root", "");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from User");
			while (rs.next()) {
				String email = rs.getString(1);
				String password = rs.getString(2);
				String roles = rs.getString(3);
				userList.add(new User(email, password, roles));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

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

	public User getUserByEmail(String email) {
		Connection con;
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "2FeroT8WC0GG");

			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM User where email='" + email + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// int Id = rs.getInt(1);
				String emailID = rs.getString(1);
				String password = rs.getString(2);
				String roles = rs.getString(3);
				// String screening = rs.getString(5);
				// String type = rs.getString(6);
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
