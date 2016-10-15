package ulink.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ulink.constructor.Client;

public class DatabaseConnection {

	public ArrayList<Client> retrieveAllClient() {

		Connection con;
		ArrayList<Client> clientList = new ArrayList<Client>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ulink", "root", "");
			
			Statement stmt = con.createStatement();
			String sql = "Select * from client";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 String passportNumber = rs.getString(1);
				 String clientName = rs.getString(2);
				 String gender = rs.getString(3);
				 String dateOfBirth = rs.getString(4);
				 String mainDianosis = rs.getString(5);
				 String nationality = rs.getString(6);
				 String countryOfResidence = rs.getString(7);
				 String billingStreet = rs.getString(8);
				 String billingCity = rs.getString(9);
				 String billingState = rs.getString(10);
				 String billingCountry = rs.getString(11);
				 String billingCode = rs.getString(12);
				 String isMedicial = rs.getString(13);
				 String isClaim = rs.getString(14);
				 String claimInformation = rs.getString(15);
				 String referralName = rs.getString(16);
				clientList.add(new Client(passportNumber, clientName, gender, dateOfBirth,
						mainDianosis, nationality, countryOfResidence, billingStreet,billingCity, billingState, billingCountry, billingCode, isMedicial, isClaim,  claimInformation,referralName));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientList;
	}
	
}
