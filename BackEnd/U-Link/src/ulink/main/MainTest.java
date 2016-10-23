package ulink.main;

import java.util.ArrayList;
import java.util.HashMap;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		TopK test = new TopK();
		
		HashMap<String,Integer>test1 = test.topDoctor("2016-10-05 00:00:00","2016-10-05 00:00:00");
		HashMap<String,Integer>test2 = test.topReferral("2016-10-01 00:00:00","2016-10-20 00:00:00");
		HashMap<String,Integer>test3 = test.topSpeciality();
		System.out.println(test1.toString());
		System.out.println(test2.toString());
		System.out.println(test3.toString());
	}

}
