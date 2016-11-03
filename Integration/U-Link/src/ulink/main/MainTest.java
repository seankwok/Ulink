package ulink.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.AgeGender;
import ulink.logic.TimeLine;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		TopK test = new TopK();
		TimeLine timeline = new TimeLine();
		HashMap<String,Integer>test1 = test.topDoctor("2016-10-05 00:00:00","2016-10-05 00:00:00");
		//HashMap<String,Integer>test2 = test.topReferral("2016-10-01 00:00:00","2016-10-20 00:00:00");
		//HashMap<String,Integer>test3 = test.topSpeciality();
		HashMap<String, Integer>test4 = test.compareTeam("2015-10-05 00:00:00","2017-10-20 00:00:00","Visa");
		//timeline.sendEmail();
		//timeline.addScreeningToTimeline();
		HashMap<String,Integer> test5 = timeline.displayAllScreening();
		System.out.println(test1.toString());
		//System.out.println(test2.toString());
		///System.out.println(test3.toString());
		System.out.println(test4.toString());
		//System.out.println(test5.toString());
		//DateTime datetime = new DateTime();
		//int currentYear = datetime.getYear();
		//System.out.println(currentYear);
		AgeGender genderByAge = new AgeGender();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		System.out.println(clientList.get(1).getDateOfBirth());

	}

}
