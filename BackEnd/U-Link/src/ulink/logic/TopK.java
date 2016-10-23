package ulink.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import ulink.constructor.Client;
import ulink.constructor.Consultation;
import ulink.dao.DatabaseConnection;

public class TopK {
	DatabaseConnection connection = new DatabaseConnection();
	
	public HashMap<String,Integer> topReferral(String startDate, String endDate){
		ArrayList<Client> clientList = connection.retrieveAllClient(startDate, endDate);
		HashMap<String,Integer> referralList = new HashMap<String,Integer>();
		
		
		for (int i = 0; i < clientList.size(); i++){
			Client client = clientList.get(i);
			String referralName = client.getReferralName();
			if (referralName != null){
				if (!referralList.containsKey(referralName)){
					referralList.put(referralName, 1);
				}else{
					referralList.put(referralName, referralList.get(referralName)+1);
				}
			}
		}
		
		   HashMap<String, Integer> sortedReferral = sortByValue(referralList);
		
		   return sortedReferral;
	
	}
	
	public HashMap<String,Integer> topSpeciality(){
		ArrayList<String> specialityList = connection.retrieveAllSpeciality();
		HashMap<String,Integer> specialityHash = new HashMap<String,Integer>();
		
		
		for (int i = 0; i < specialityList.size(); i++){
			String speciality = specialityList.get(i);
			if (speciality != null){
				if (!specialityHash.containsKey(speciality)){
					specialityHash.put(speciality, 1);
				}else{
					specialityHash.put(speciality, specialityHash.get(speciality)+1);
				}
			}
		}
		
		   HashMap<String, Integer> sortedCondition = sortByValue(specialityHash);
		
		   return sortedCondition;
	
	}
	
	public HashMap<String,Integer> topDoctor(String startDate, String endDate){
		ArrayList<Consultation> consultationList = connection.retrieveAllConsultation(startDate,endDate);
		HashMap<String,Integer> doctorList = new HashMap<String,Integer>();
		
		
		for (int i = 0; i < consultationList.size(); i++){
			Consultation consultation = consultationList.get(i);
			String doctorName  = consultation.getDoctorName();
			if (doctorName != null){
				if (!doctorList.containsKey(doctorName)){
					doctorList.put(doctorName, 1);
				}else{
					doctorList.put(doctorName, doctorList.get(doctorName)+1);
				}
			}
		}
		
		   HashMap<String, Integer> sortedDoctor = sortByValue(doctorList);
		
		   return sortedDoctor;
	
	}
	
	 private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<HashMap.Entry<String, Integer>> list =
	                new LinkedList<HashMap.Entry<String, Integer>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
	            public int compare(HashMap.Entry<String, Integer> o1,
	            		HashMap.Entry<String, Integer> o2) {
	                return (o2.getValue()).compareTo(o1.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (HashMap.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedMap;
	    }
	
}