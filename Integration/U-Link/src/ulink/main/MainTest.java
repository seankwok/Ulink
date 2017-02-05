package ulink.main;

import java.io.File;
import java.io.FileInputStream;
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
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		
		//ArrayList<Condition> conditionList = connection.retrieveAllConditionBySort("conditionName", "DESC");
		
	//	System.out.print(conditionList.size());
		
		ArrayList<String> list = connection.retrievePastSixMonthRecord("Medical", connection.retrieveLatestDate());
		LinkedHashMap<String,Integer> pastSixMonth = new LinkedHashMap<>();
		
		Utility utility = new Utility();
		
		for (int i =0; i < list.size(); i++){
			
			System.out.println(utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7))));
			String month = utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7)));
			if (pastSixMonth.containsKey(month)){
				int temp = pastSixMonth.get(month);
				pastSixMonth.put(month, temp+1);
			} else {
				pastSixMonth.put(month, 1);
			}
		}
	
		System.out.println(pastSixMonth.keySet());
		System.out.println(connection.retrieveLatestDate());
			
		}
	}


