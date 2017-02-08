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
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> list = connection.retrieveAllClientList();
		LinkedHashMap<String,Integer> visaTypeList = new LinkedHashMap<>();
		
		Utility utility = new Utility();
		
		for (int i=0; i <list.size(); i++){
			Client c = list.get(i);
			if(c.getVisaType().length() >= 1){
			if (visaTypeList.containsKey(c.getVisaType())){
				int temp = visaTypeList.get(c.getVisaType());
				visaTypeList.put(c.getVisaType(), temp+1);
			}else {
				visaTypeList.put(c.getVisaType(), 1);
			}
			}
			System.out.println(visaTypeList.keySet());
		}
		
			
		}
	}


