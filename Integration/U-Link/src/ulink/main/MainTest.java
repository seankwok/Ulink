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
import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		//String team = request.getParameter("team");
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> list = connection.retrieveAllClientList();
		LinkedHashMap<String,Integer> visaTypeList = new LinkedHashMap<>();
		
		Utility utility = new Utility();
		
		String startDate = "2015-05-05";
		System.out.println(utility.getMonth(Integer.parseInt(startDate.substring(5,7))));
	}

}
