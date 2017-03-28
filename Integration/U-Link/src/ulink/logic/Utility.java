package ulink.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.joda.time.DateTime;

import ulink.constructor.Condition;
import ulink.constructor.Index;
import ulink.constructor.User;
import ulink.dao.DatabaseConnection;
public class Utility {


	
	
	public String getStartDateOfMonth(String date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		
		
		return date.substring(0, 8) + c.getActualMinimum(Calendar.DAY_OF_MONTH);
	}
	
	public String getEndDateOfMonth(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		
		return date.substring(0, 8) + c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public TreeMap<Integer, ArrayList<String>> returnTypeList(ArrayList<Condition> conditionList) {
		TreeMap<Integer, ArrayList<String>> typeList = new TreeMap<Integer, ArrayList<String>>();
		ArrayList<String> conditionListByAge;

		for (int i = 0; i < conditionList.size(); i++) {
			Condition condition = conditionList.get(i);

			if (typeList.containsKey(condition.getAgeRequired())) {
				conditionListByAge = typeList.get(condition.getAgeRequired());
				conditionListByAge.add(condition.getConditionName());
			} else {
				conditionListByAge = new ArrayList<>();
				conditionListByAge.add(condition.getConditionName());
				typeList.put(condition.getAgeRequired(), conditionListByAge);
			}

		}

		return typeList;
	}
	
	public String countMean(HashMap<Integer,Integer> values, int size){
		double sum = 0.0;
		for (int key : values.keySet()) {
		    int value = values.get(key);
		    sum += 1.0 * key * value;
		}
		DecimalFormat two = new DecimalFormat("0.00");
		String mean = two.format(sum/size);
		return mean;
	}
	
	public String countSD(HashMap<Integer,Integer> values, int size){
		double sum = 0.0;
		for (int key : values.keySet()) {
			int value = values.get(key);
		    sum += 1.0 * key * value;
		}
		DecimalFormat two = new DecimalFormat("0.00");
		String mean = two.format(sum/size);
		
        double temp = 0;
        for (int key : values.keySet()) {
        	int a = values.get(key);
            temp += (a-sum/size)*(a-sum/size);
        }
        
		return two.format(Math.sqrt(temp));
	}
	
	public LinkedHashMap<Integer,Double> getIndexCount(ArrayList<Index> indexList){
		
		LinkedHashMap<Integer, Double> pointSystem = new LinkedHashMap<>();
		LinkedHashMap<Integer, String> returnList = new LinkedHashMap<>();
		
		pointSystem.put(0,0.0);
		pointSystem.put(1,0.0);
		pointSystem.put(2,0.0);
		pointSystem.put(3,0.0);

		for (int i = 0; i < indexList.size(); i++) {
			int point = 0;
			
			Index index = indexList.get(i);
			if (index.getAddress().length() > 0) {
				//System.out.println(index.getAddress());
				point++;
				
			}
			if (index.getEmail().length() > 0) {
				//System.out.println(index.getEmail());
				point++;
				
			}
			if (index.getPhone().length() > 0) {
				//System.out.println(index.getPhone());
				point++;
				
			}
			//System.out.print(point);
			if (pointSystem.containsKey(point)){
				double temp = pointSystem.get(point);
				pointSystem.put(point, temp+1);
				//System.out.println(point + " " + temp+1);
			}
		}
		
		double sum = pointSystem.get(0) + pointSystem.get(1) + pointSystem.get(2) + pointSystem.get(3); 
		double zero = pointSystem.get(0);
		double one = pointSystem.get(1);
		double two = pointSystem.get(2);
		double three = pointSystem.get(3);
		
		pointSystem.put(0, (double) Math.round(zero/sum *100));
		pointSystem.put(1, (double) Math.round(one/sum *100));
		pointSystem.put(2, (double) Math.round(two/sum *100));
		pointSystem.put(3, (double) Math.round(three/sum *100));
		
		return pointSystem;
		
	}
	
	public String changeDateFormat(String date){
		return date.substring(6,10)+ "-" + date.substring(0, 2) + "-" + date.substring(3, 5);
	}
	public String changeDisplayDateFormat(String date){
		return date.substring(8,10)+ "-" + date.substring(5, 7) + "-"+date.substring(0,4);
	}
	public String changeDateFormatDatabase(String date){
		return date.substring(6,10)+ "/" + date.substring(3, 5) + "/" + date.substring(0, 2);
	}
	
	public String changeDateExportFormat(String date){
		return date.substring(0,4)+ "/" + date.substring(5, 7) + "/" + date.substring(8);
	}
	
	public int getAge(String dob) {
		if (dob.length() > 0 && dob != null && dob != ""){
			dob = dob.replace('/', '-');
		int year = Integer.parseInt(dob.substring(0,4));
		
		DateTime datetime = new DateTime();
		int currentYear = datetime.getYear();

		return currentYear - year;
		} else {
			return 0;
		}
	}
	
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	public String hash(String password) {
		  StringBuffer sb = new StringBuffer();
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(password.getBytes());
	        
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	      
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return sb.toString();
	       
}
	
	public boolean loginValidatation(String username, String password){
		boolean isValid = false;
		DatabaseConnection database = new DatabaseConnection();
		Utility utility = new Utility();

		ArrayList<User> userList = database.getUser();
		
		for (int i=0; i <userList.size(); i++){
			User user = userList.get(i);
			String hPassword = utility.hash(password);
			if (username.toLowerCase().equals(user.getEmail().toLowerCase()) && hPassword.equals(user.getPassword())){
				isValid = true;
			}
		}
		
		return isValid;
	}
}
