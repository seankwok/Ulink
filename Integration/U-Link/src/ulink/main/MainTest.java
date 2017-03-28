package ulink.main;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import org.apache.poi.util.SystemOutLogger;
import org.joda.time.DateTimeZone;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
import ulink.constructor.Index;
import ulink.constructor.PersonInCharge;
import ulink.constructor.RankingDoctorSpecialty;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Email;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		Utility utility = new Utility();
		ArrayList<PersonInCharge> listAllPIC = new ArrayList<>();
	//	LinkedHashMap<String,LinkedHashMap<Integer,Double>> personInChargePointSystem = new  LinkedHashMap<String,LinkedHashMap<Integer,Double>>();
		for (int i = 0; i<personInChargeList.size(); i++){
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(utility.changeDateFormatDatabase("01-01-2015"), utility.changeDateFormatDatabase("01-01-2017"), "Visa",temp);	
			
			
		System.out.println(indexList.size());
			LinkedHashMap<Integer,Double> pointSystem = utility.getIndexCount(indexList);
			
			listAllPIC.add(new PersonInCharge(temp, pointSystem));
		}
		
		for (int i= 0; i< personInChargeList.size();i++){
			System.out.println(personInChargeList.get(i));
		}
		
		for (int i =0; i< listAllPIC.size(); i++){
			System.out.println(listAllPIC.get(i).getPointSystem().values());
		}
	
		
		
	}
}