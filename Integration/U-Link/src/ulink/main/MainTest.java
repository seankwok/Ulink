package ulink.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) {
DatabaseConnection connection = new DatabaseConnection();
		
		LocalDate myDate =LocalDate.parse(connection.retrieveLatestDate());
		String date = myDate.minusMonths(1).toString();
		Utility utility = new Utility();
		String month = utility.getMonth(Integer.parseInt(date.substring(5, 7)));
		
		System.out.println(month);
		
	
	}

}
