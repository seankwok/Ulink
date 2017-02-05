package ulink.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.Consultation;
import ulink.constructor.KPI;
import ulink.dao.DatabaseConnection;

public class TopK {
	DatabaseConnection connection = new DatabaseConnection();

	public ArrayList<AgeAndGender> getAgeGenderReport() {
		ArrayList<AgeAndGender> ageGenderReport = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		int m10 = 0;
		int f10 = 0;
		int m20 = 0;
		int f20 = 0;
		int m30 = 0;
		int f30 = 0;
		int m40 = 0;
		int f40 = 0;
		int m50 = 0;
		int f50 = 0;
		int m60 = 0;
		int f60 = 0;
		int m70 = 0;
		int f70 = 0;
		int m80 = 0;
		int f80 = 0;

		for (int i = 1; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (client.getGender() != null) {
				if (client.getGender().equals("Male")) {
					if (client.getAge() <= 10) {
						m10++;
					} else if (client.getAge() <= 20) {
						m20++;
					} else if (client.getAge() <= 30) {
						m30++;
					} else if (client.getAge() <= 40) {
						m40++;
					} else if (client.getAge() <= 50) {
						m50++;
					} else if (client.getAge() <= 60) {
						m60++;
					} else if (client.getAge() <= 70) {
						m70++;
					} else {
						m80++;
					}
				} else {
					if (client.getAge() <= 10) {
						f10++;
					} else if (client.getAge() <= 20) {
						f20++;
					} else if (client.getAge() <= 30) {
						f30++;
					} else if (client.getAge() <= 40) {
						f40++;
					} else if (client.getAge() <= 50) {
						f50++;
					} else if (client.getAge() <= 60) {
						f60++;
					} else if (client.getAge() <= 70) {
						f70++;
					} else {
						f80++;
					}
				}
			}

		}
		int total10 = 0;
		int total20 = 0;
		int total30 = 0;
		int total40 = 0;
		int total50 = 0;
		int total60 = 0;
		int total70 = 0;
		int total80 = 0;
		if (clientList.size() != 0) {
			total10 = (m10 + f10) / clientList.size();
			total20 = (m10 + f10) / clientList.size();
			total30 = (m10 + f10) / clientList.size();
			total40 = (m10 + f10) / clientList.size();
			total50 = (m10 + f10) / clientList.size();
			total60 = (m10 + f10) / clientList.size();
			total70 = (m10 + f10) / clientList.size();
			total80 = (m10 + f10) / clientList.size();
		}
		
		int totalm10 = 0;
		int totalm20 = 0;
		int totalm30 = 0;
		int totalm40 = 0;
		int totalm50 = 0;
		int totalm60 = 0;
		int totalm70 = 0;
		int totalm80 = 0;
		
		if (m10+f10 != 0){
			totalm10 = m10 / (m10 + f10);
		}
		
		if (m20+f20 != 0){
			totalm20 = m20 / (m20 + f20);
		}
		
		if (m30+f30 != 0){
			totalm30 = m30 / (m30 + f30);
		}
		
		if (m40+f40 != 0){
			totalm40 = m40 / (m40 + f40);
		}
		
		if (m50+f50 != 0){
			totalm50 = m50 / (m50 + f50);
		}
		
		if (m60+f60 != 0){
			totalm60 = m60 / (m60 + f60);
		}
		
		if (m70+f70 != 0){
			totalm70 = m70 / (m70 + f70);
		}
		
		if (m80+f80 != 0){
			totalm80 = m80 / (m80 + f80);
		}
		
		int totalf10 = 0;
		int totalf20 = 0;
		int totalf30 = 0;
		int totalf40 = 0;
		int totalf50 = 0;
		int totalf60 = 0;
		int totalf70 = 0;
		int totalf80 = 0;
		
		if (m10+f10 != 0){
			totalf10 = f10 / (m10 + f10);
		}
		
		if (m20+f20 != 0){
			totalf20 = f20 / (m20 + f20);
		}
		
		if (m30+f30 != 0){
			totalf30 = f30 / (m30 + f30);
		}
		
		if (m40+f40 != 0){
			totalf40 = f40 / (m40 + f40);
		}
		
		if (m50+f50 != 0){
			totalf50 = f50 / (m50 + f50);
		}
		
		if (m60+f60 != 0){
			totalf60 = f60 / (m60 + f60);
		}
		
		if (m70+f70 != 0){
			totalf70 = f70 / (m70 + f70);
		}
		
		if (m80+f80 != 0){
			totalf80 = f80 / (m80 + f80);
		}
		


		ageGenderReport.add(new AgeAndGender("10", totalm10, totalf10, total10));
		ageGenderReport.add(new AgeAndGender("20", totalm20, totalf20, total20));
		ageGenderReport.add(new AgeAndGender("30", totalm30, totalf30, total30));
		ageGenderReport.add(new AgeAndGender("40", totalm40, totalf40, total40));
		ageGenderReport.add(new AgeAndGender("50", totalm50, totalf50, total50));
		ageGenderReport.add(new AgeAndGender("60", totalm60, totalf60, total60));
		ageGenderReport.add(new AgeAndGender("70", totalm70, totalf70, total70));
		ageGenderReport.add(new AgeAndGender("80", totalm80, totalf80, total80));

		return ageGenderReport;
	}

	public KPI getKPI(String type, String date) {
		// ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type);
		int in = 0;
		int out = 0;

		for (int i = 1; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (client.getCreatedtime().contains(date) && client.getHospitalAdmitted() != null) {
				in++;
			} else if (client.getCreatedtime().contains(date) && client.getHospitalAdmitted() == null) {
				out++;
			}
		}

		Utility utility = new Utility();

		String month = utility.getMonth(Integer.parseInt(date.substring(5)));

		return new KPI(month + "-" + date.substring(0, 4), in, out);
	}

	public KPI getKPIVisa(String type, String date) {
		// ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type);
		int in = 0;
		int out = 0;

		for (int i = 1; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (client.getCreatedtime().contains(date) && client.getVisaType().contains("Indonesia")) {
				in++;
			} else if (client.getCreatedtime().contains(date)) {
				out++;
			}
		}

		return new KPI(date, in, out);
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
		for (int i = 0; i < compareTeamList.size() - 1; i += 2) {
			String nationality = compareTeamList.get(i);
			String hospitalAdmitted = compareTeamList.get(i + 1);
			System.out.println(nationality + " <<<< COUNTRY" + i);
			System.out.println(hospitalAdmitted + "   " + (i + 1));
			if (nationality.equals("Indonesian")) {
				int temp = compareList.get("indo");
				compareList.put("indo", temp + 1);
			} else {
				int temp = compareList.get("nonIndo");
				compareList.put("nonIndo", temp + 1);
			}

			if (hospitalAdmitted != null) {
				int temp = compareList.get("inPatient");
				compareList.put("inPatient", temp + 1);
			} else {
				int temp = compareList.get("outPatient");
				compareList.put("outPatient", temp + 1);
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