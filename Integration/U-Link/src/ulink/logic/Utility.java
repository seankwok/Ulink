package ulink.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.Properties;
import java.util.TreeMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;

import ulink.constructor.Condition;
import ulink.constructor.User;
import ulink.dao.DatabaseConnection;
import java.text.DateFormatSymbols;
public class Utility {

	public boolean sendMail(String emailTo, String emailFrom, String host) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailFrom));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}

	}

	public TreeMap<Integer, ArrayList<String>> returnTypeList(ArrayList<Condition> conditionList) {
		TreeMap<Integer, ArrayList<String>> typeList = new TreeMap<Integer, ArrayList<String>>();
		ArrayList<String> conditionListByAge;

		for (int i = 0; i < conditionList.size(); i++) {
			Condition condition = conditionList.get(i);

			if (typeList.containsKey(condition.getAgeRequired())) {
				conditionListByAge = typeList.get(condition.getAgeRequired());
				conditionListByAge.add(condition.getConditionName());
			} else {
				conditionListByAge = new ArrayList<>();
				conditionListByAge.add(condition.getConditionName());
				typeList.put(condition.getAgeRequired(), conditionListByAge);
			}

		}

		return typeList;
	}
	
	public String changeDateFormat(String date){
		return date.substring(6,10)+ "-" + date.substring(0, 2) + "-" + date.substring(3, 5);
	}
	public String changeDisplayDateFormat(String date){
		return date.substring(8,10)+ "-" + date.substring(5, 7) + "-"+date.substring(0,4);
	}
	
	public int getAge(String dob) {
		if (dob.length() > 0 && dob != null && dob != ""){
			dob = dob.replace('/', '-');
		int year = Integer.parseInt(dob.substring(6));
		System.out.println(year);
		DateTime datetime = new DateTime();
		int currentYear = datetime.getYear();

		return currentYear - year;
		} else {
			return 0;
		}
	}
	
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	public String hash(String password) {
		  StringBuffer sb = new StringBuffer();
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(password.getBytes());
	        
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	      
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return sb.toString();
	       
}
	
	public boolean loginValidatation(String username, String password){
		boolean isValid = false;
		DatabaseConnection database = new DatabaseConnection();
		Utility utility = new Utility();

		ArrayList<User> userList = database.getUser();
		
		for (int i=0; i <userList.size(); i++){
			User user = userList.get(i);
			String hPassword = utility.hash(password);
			if (username.toLowerCase().equals(user.getEmail().toLowerCase()) && hPassword.equals(user.getPassword())){
				isValid = true;
			}
		}
		
		return isValid;
	}
}
