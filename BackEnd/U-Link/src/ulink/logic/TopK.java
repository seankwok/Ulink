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

	public HashMap<String, Integer> topReferral(String startDate, String endDate) {
		ArrayList<Client> clientList = connection.retrieveAllClient(startDate, endDate);
		HashMap<String, Integer> referralList = new HashMap<String, Integer>();

		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			String referralName = client.getReferredByPIC();
			if (referralName != null) {
				if (!referralList.containsKey(referralName)) {
					referralList.put(referralName, 1);
				} else {
					referralList.put(referralName, referralList.get(referralName) + 1);
				}
			}
		}
		
		HashMap<String, Integer> sortedReferral = sortByValue(referralList);

		return sortedReferral;

	}

	public HashMap<String, Integer> topSpeciality(String startDate, String endDate) {
		ArrayList<String> specialityList = connection.retrieveAllSpeciality(startDate, endDate);
		HashMap<String, Integer> specialityHash = new HashMap<String, Integer>();

		for (int i = 0; i < specialityList.size(); i++) {
			String speciality = specialityList.get(i);
			if (speciality != null) {
				if (!specialityHash.containsKey(speciality)) {
					specialityHash.put(speciality, 1);
				} else {
					specialityHash.put(speciality, specialityHash.get(speciality) + 1);
				}
			}
		}

		HashMap<String, Integer> sortedCondition = sortByValue(specialityHash);

		return sortedCondition;

	}

	public HashMap<String, Integer> topDoctor(String startDate, String endDate) {
		ArrayList<Consultation> consultationList = connection.retrieveAllConsultation(startDate, endDate);
		HashMap<String, Integer> doctorList = new HashMap<String, Integer>();

		for (int i = 0; i < consultationList.size(); i++) {
			Consultation consultation = consultationList.get(i);
			String doctorName = consultation.getDoctorName();
			if (doctorName != null) {
				if (!doctorList.containsKey(doctorName)) {
					doctorList.put(doctorName, 1);
				} else {
					doctorList.put(doctorName, doctorList.get(doctorName) + 1);
				}
			}
		}

		HashMap<String, Integer> sortedDoctor = sortByValue(doctorList);

		return sortedDoctor;

	}

	public HashMap<String, Integer> compareTeam(String startDate, String endDate, String team) {
		ArrayList<String> compareTeamList = connection.retrieveAllTeam(startDate, endDate, team);
		HashMap<String, Integer> compareList = new HashMap<String, Integer>();
		compareList.put("indo", 0);
		compareList.put("nonIndo", 0);
		compareList.put("inPatient", 0);
		compareList.put("outPatient", 0);
		// hospitalAdmitted = null means out
	System.out.println(endDate);
		for (int i = 0; i < compareTeamList.size() - 1; i+=2) {
			String nationality = compareTeamList.get(i);
			String hospitalAdmitted = compareTeamList.get(i + 1);
			System.out.println(nationality + " <<<< COUNTRY" + i);
			System.out.println(hospitalAdmitted+ "   " + (i+1));
			if (nationality.equals("Indonesian")) {
				int temp = compareList.get("indo");
				compareList.put("indo", temp + 1);
			} else {
				int temp = compareList.get("nonIndo");
				compareList.put("nonIndo", temp + 1);
			}
			
			if (hospitalAdmitted != null){
				int temp = compareList.get("inPatient");
				compareList.put("inPatient",temp+1);
			} else {
				int temp = compareList.get("outPatient");
				compareList.put("outPatient",temp+1);
			}
	}

	return compareList;

	}

	private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortMap) {

		// 1. Convert Map to List of Map
		List<HashMap.Entry<String, Integer>> list = new LinkedList<HashMap.Entry<String, Integer>>(
				unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
			public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (HashMap.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		/*
		 * //classic iterator example for (Iterator<Map.Entry<String, Integer>>
		 * it = list.iterator(); it.hasNext(); ) { Map.Entry<String, Integer>
		 * entry = it.next(); sortedMap.put(entry.getKey(), entry.getValue()); }
		 */

		return sortedMap;
	}

}