package ulink.logic;

import java.util.ArrayList;
import java.util.TreeMap;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;

public class AgeGender {

	public TreeMap<String, Integer> genderByAge() {
		Utility utility = new Utility();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		TreeMap<String, Integer> genderByAgeList = new TreeMap<String, Integer>();
		genderByAgeList.put("Male 0 - 10", 0);
		genderByAgeList.put("Male 10 - 20", 0);
		genderByAgeList.put("Male 20 - 30", 0);
		genderByAgeList.put("Male 30 - 40", 0);
		genderByAgeList.put("Male 40 - 50", 0);
		genderByAgeList.put("Male 50 - 60", 0);
		genderByAgeList.put("Male 60 - 70", 0);
		genderByAgeList.put("Male 70 - 80", 0);
		genderByAgeList.put("Male Above 80", 0);

		genderByAgeList.put("Female 0 - 10", 0);
		genderByAgeList.put("Female 10 - 20", 0);
		genderByAgeList.put("Female 20 - 30", 0);
		genderByAgeList.put("Female 30 - 40", 0);
		genderByAgeList.put("Female 40 - 50", 0);
		genderByAgeList.put("Female 50 - 60", 0);
		genderByAgeList.put("Female 60 - 70", 0);
		genderByAgeList.put("Female 70 - 80", 0);
		genderByAgeList.put("Female Above 80", 0);

		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			String dob = client.getDateOfBirth();
			int birthYear = Integer.parseInt(dob.substring(6));
			int age = utility.getAge(birthYear);
			String gender = client.getGender();
			int temp;
			if (age <= 10 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 0 - 10");
				genderByAgeList.put("Male 0 - 10", temp + 1);
			} else if (age <= 20 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 10 - 20");
				genderByAgeList.put("Male 10 - 20", temp + 1);
			} else if (age <= 30 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 20 - 30");
				genderByAgeList.put("Male 20 - 30", temp + 1);
			} else if (age <= 40 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 30 - 40");
				genderByAgeList.put("Male 30 - 40", temp + 1);
			} else if (age <= 50 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 40 - 50");
				genderByAgeList.put("Male 40 - 50", temp + 1);
			} else if (age <= 60 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 50 - 60");
				genderByAgeList.put("Male 50 - 60", temp + 1);
			} else if (age <= 70 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 60 - 70");
				genderByAgeList.put("Male 60 - 70", temp + 1);
			} else if (age <= 80 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male 70 - 80");
				genderByAgeList.put("Male 70 - 80", temp + 1);
			} else if (age >= 80 && gender.equals("Male")) {
				temp = genderByAgeList.get("Male Above 80");
				genderByAgeList.put("Male Above 80", temp + 1);
			} else if (age <= 10 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 0 - 10");
				genderByAgeList.put("Female 0 - 10", temp + 1);
			} else if (age <= 20 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 10 - 20");
				genderByAgeList.put("Female 10 - 20", temp + 1);
			} else if (age <= 30 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 20 - 30");
				genderByAgeList.put("Female 20 - 30", temp + 1);
			} else if (age <= 40 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 30 - 40");
				genderByAgeList.put("Female 30 - 40", temp + 1);
			} else if (age <= 50 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 40 - 50");
				genderByAgeList.put("Female 40 - 50", temp + 1);
			} else if (age <= 60 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 50 - 60");
				genderByAgeList.put("Female 50 - 60", temp + 1);
			} else if (age <= 70 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 60 - 70");
				genderByAgeList.put("Female 60 - 70", temp + 1);
			} else if (age <= 80 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female 70 - 80");
				genderByAgeList.put("Female 70 - 80", temp + 1);
			} else if (age >= 80 && gender.equals("Female")) {
				temp = genderByAgeList.get("Female Above 80");
				genderByAgeList.put("Female Above 80", temp + 1);
			}

		}

		return genderByAgeList;
	}

}
