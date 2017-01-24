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
		
		String test = "2016-01";
        // System.out.println(test.substring(5));
		
         Utility utility = new Utility();
        System.out.print(utility.changeDateFormatDatabase("05/06/2015"));
	}

}
