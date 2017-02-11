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

import com.google.gson.Gson;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
DatabaseConnection connection = new DatabaseConnection();
//DatabaseConnection connection = new DatabaseConnection();
	String date = connection.retrieveLatestDate();
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date convertedDate = dateFormat.parse(date);
	Calendar c = Calendar.getInstance();
	c.setTime(convertedDate);
	//c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	
	
	String temp = date.substring(0, 8) + c.getActualMaximum(Calendar.DAY_OF_MONTH);
	System.out.println(temp);

	}

}
