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
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		DatabaseConnection connection = new DatabaseConnection();
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		//String team = request.getParameter("team");
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		Utility utility = new Utility();
		LinkedHashMap<String,LinkedHashMap<Integer,Integer>> personInChargePointSystem = new  LinkedHashMap<String,LinkedHashMap<Integer,Integer>>();
		for (int i = 0; i<personInChargeList.size(); i++){
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(utility.changeDateFormatDatabase("01/01/2015"), utility.changeDateFormatDatabase("01/01/2017"),"Visa",temp);	
			LinkedHashMap<Integer,Integer> pointSystem = utility.getIndexCount(indexList);
			if (!personInChargePointSystem.containsKey(temp)){
				personInChargePointSystem.put(temp, pointSystem);
			}
		}
		
		System.out.println(personInChargePointSystem.toString());
	}

}
