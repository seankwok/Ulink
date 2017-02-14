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
	//	DatabaseConnection connection = new DatabaseConnection();
		String startDate = "2017-01-01";
		String endDate = "2017-02-02";
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         try {
			Date date1 = sdf.parse(startDate);
			Date date2 = sdf.parse(endDate);
			System.out.println(date1.before(date2));
			
			//Gson gson = new Gson();
			//PrintWriter out = response.getWriter();
			//String arrayListToJson = gson.toJson(date1.after(date2));
		
		} catch (Exception e){
			
		}

}
}