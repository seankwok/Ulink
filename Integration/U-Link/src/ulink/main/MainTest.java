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
import ulink.constructor.RankingDoctorSpecialty;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Email;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		ArrayList<RankingDoctorSpecialty> doctorList = connection.retrieveAllDoctorBySpecialty("Obs & Gynae",utility.changeDateFormatDatabase("01/01/2017"),utility.changeDateFormat("28/01/2017"));
		
		System.out.println(doctorList.size());
		
		System.out.println(doctorList.get(0).getName());


	}
}