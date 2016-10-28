package ulink.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;

public class AgeGender {
	
	public TreeMap<String,Integer> genderByAge(){
		Utility utility = new Utility();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		TreeMap<String,Integer> genderByAgeList = new TreeMap<String,Integer>();
		genderByAgeList.put("M10", 0);
		genderByAgeList.put("M20", 0);
		genderByAgeList.put("M30", 0);
		genderByAgeList.put("M40", 0);
		genderByAgeList.put("M50", 0);
		genderByAgeList.put("M60", 0);
		genderByAgeList.put("M70", 0);
		genderByAgeList.put("M80", 0);
		
		genderByAgeList.put("F10", 0);
		genderByAgeList.put("F20", 0);
		genderByAgeList.put("F30", 0);
		genderByAgeList.put("F40", 0);
		genderByAgeList.put("F50", 0);
		genderByAgeList.put("F60", 0);
		genderByAgeList.put("F70", 0);
		genderByAgeList.put("F80", 0);
		
		
		for (int i =0; i<clientList.size(); i++){
			Client client = clientList.get(i);
			String dob = client.getDateOfBirth();
			int birthYear = Integer.parseInt(dob.substring(0, 4));
			int age = utility.getAge(birthYear);
			String gender = client.getGender();
			int temp;
			if (age <=10 && gender.equals("Male")){
				temp = genderByAgeList.get("M10");
				genderByAgeList.put("M10", temp+1);
			} else if(age <=20 && gender.equals("Male")){
				temp = genderByAgeList.get("M20");
				genderByAgeList.put("M20", temp+1);
			} else if(age <=30 && gender.equals("Male")){
				temp = genderByAgeList.get("M30");
				genderByAgeList.put("M30", temp+1);
			} else if(age <=40 && gender.equals("Male")){
				temp = genderByAgeList.get("M40");
				genderByAgeList.put("M40", temp+1);
			} else if(age <=50 && gender.equals("Male")){
				temp = genderByAgeList.get("M50");
				genderByAgeList.put("M50", temp+1);
			} else if(age <=60 && gender.equals("Male")){
				temp = genderByAgeList.get("M60");
				genderByAgeList.put("M60", temp+1);
			} else if(age <=70 && gender.equals("Male")){
				temp = genderByAgeList.get("M70");
				genderByAgeList.put("M70", temp+1);
			} else if(age <=80 && gender.equals("Male")){
				temp = genderByAgeList.get("M80");
				genderByAgeList.put("M80", temp+1);
			} else if(age <=10 && gender.equals("Female")){
				temp = genderByAgeList.get("F10");
				genderByAgeList.put("F10", temp+1);
			} else if(age <=20 && gender.equals("Female")){
				temp = genderByAgeList.get("F20");
				genderByAgeList.put("F20", temp+1);
			}  else if(age <=30 && gender.equals("Female")){
				temp = genderByAgeList.get("F30");
				genderByAgeList.put("F30", temp+1);
			}  else if(age <=40 && gender.equals("Female")){
				temp = genderByAgeList.get("F40");
				genderByAgeList.put("F40", temp+1);
			}  else if(age <=50 && gender.equals("Female")){
				temp = genderByAgeList.get("F50");
				genderByAgeList.put("F50", temp+1);
			}  else if(age <=60 && gender.equals("Female")){
				temp = genderByAgeList.get("F60");
				genderByAgeList.put("F60", temp+1);
			}  else if(age <=70 && gender.equals("Female")){
				temp = genderByAgeList.get("F70");
				genderByAgeList.put("F70", temp+1);
			}  else if(age <=80 && gender.equals("Female")){
				temp = genderByAgeList.get("F80");
				genderByAgeList.put("F80", temp+1);
			}   
		
			
		}
		
		
		return genderByAgeList;
	}
	
}
