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
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		//String team = request.getParameter("team");
		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		ArrayList<Index> indexList = connection.retrieveAllIndex("2015/01/01", "2017/01/01", "Visa");
		LinkedHashMap<Integer,Integer> pointSystem = utility.getIndexCount(indexList);
		Gson gson = new Gson();
		System.out.println(indexList.size());
		//PrintWriter out = response.getWriter();
		System.out.println(pointSystem.toString());
		System.out.println(utility.changeDateFormatDatabase("01/01/2016"));
	}

}
