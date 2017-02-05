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
		
		TopK topk = new TopK();
		
		ArrayList<AgeAndGender> test = topk.getAgeGenderReport();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		for (int x =0; x < clientList.size(); x++){
			System.out.println(clientList.get(x).getAge());
		}
		System.out.println("size " + clientList.size());
		
		for(int i = 0 ; i <test.size(); i++){
			System.out.println(test.get(i).getFemale() +" " + test.get(i).getMale() +" "+ test.get(i).getTotal());
			
		}
	}

}
