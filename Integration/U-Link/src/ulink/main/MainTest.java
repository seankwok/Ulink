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
//DatabaseConnection connection = new DatabaseConnection();
ArrayList<String> emailList = connection.retrieveAllEmail();
	System.out.println(emailList.size());
	
	}

}
