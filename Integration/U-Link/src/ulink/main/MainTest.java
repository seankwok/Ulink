package ulink.main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		
		//ArrayList<Condition> conditionList = connection.retrieveAllConditionBySort("conditionName", "DESC");
		
	//	System.out.print(conditionList.size());
		
		String test = "17/05/2016 12:33";
         System.out.println(test.substring(6,10) +"-" +  test.substring(3,5) + " "+ test.substring(0,2));

	}

}
