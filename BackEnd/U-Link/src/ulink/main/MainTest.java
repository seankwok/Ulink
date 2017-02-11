package ulink.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ulink.constructor.Index;
import ulink.dao.DatabaseConnection;

public class MainTest {

	public static void main(String[] args) {
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Index> indexList = connection.retrieveAllIndex("2015-01-01", "2017-01-01", "Medical");
		HashMap<Integer, Integer> pointSystem = new HashMap<>();

		for (int i = 0; i < indexList.size(); i++) {
			int point = 0;
			Index index = indexList.get(i);
			if (index.getAddress().length() > 0) {
				point++;
			}
			if (index.getEmail().length() > 0) {
				point++;
			}
			if (index.getPhone().length() > 0) {
				point++;
			}
			if (pointSystem.containsKey(point)){
				int temp = pointSystem.get(point);
				pointSystem.put(point, temp+1);
			} else {
				pointSystem.put(point, 0);
			}
		}
		
		System.out.println(pointSystem.values());

	}

}
