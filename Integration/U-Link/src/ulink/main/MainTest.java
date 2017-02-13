package ulink.main;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.util.SystemOutLogger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Email;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		DatabaseConnection connection = new DatabaseConnection();
		//DatabaseConnection connection = new DatabaseConnection();
		//Condition condition = connection.retrieveAllConditionByID(ID);
		//String subject = request.getParameter("subject");
		//String msg = request.getParameter("msg");
		String msg = "[screening] [clientName] [clientEmail]";
		msg = msg.replace("[screening]", "Dying");
		
		
		//boolean check = true;
		//for(int i = 0; i < email.length; i++) {
			msg = msg.replace("[clientName]", "dumbdumb");
			
			msg = msg.replace("[clientEmail]", "dumbdumb@gamil");
			System.out.println(msg);
	//		check = emailServer.sendEmail(email[i], subject, msg);
	
			
			Email email = new Email();
			email.sendEmail("qwe", "qwe", msg, "qwe");
		}

}
