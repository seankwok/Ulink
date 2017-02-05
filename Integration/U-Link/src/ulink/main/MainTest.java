package ulink.main;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.Condition;
import ulink.constructor.RankingDoctor;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();

		
		ArrayList<RankingDoctor> list = connection.retrieveAllRankingDoctorDashBoard(connection.retrieveLatestDate());
		LinkedHashMap<String,Integer> visaTypeList = new LinkedHashMap<>();
		
		
		
		//Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-14");
		LocalDate myDate =LocalDate.parse("2014-02-14");
			System.out.print(myDate.minusMonths(1).toString());
			
		}
	}


