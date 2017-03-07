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

	
	Calendar startCalendar = new GregorianCalendar();
	Date current = new Date();
	Date dob = new Date(2013,12,2);
	String date = "12/11/2015";
	System.out.println(Integer.parseInt(date.substring(0,2)) + Integer.parseInt(date.substring(3,5)) + Integer.parseInt(date.substring(6)));
	
		
	}
}