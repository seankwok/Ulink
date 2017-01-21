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

	public ArrayList<AgeAndGender> getAgeGenderReport(){
		ArrayList<AgeAndGender> ageGenderReport = new ArrayList<>();
		ArrayList<Client>clientList = connection.retrieveAllClientList();
		int m10 =0;
		int f10 = 0;
		int m20 = 0;
		int f20 = 0;
		int m30 = 0;
		int f30 = 0;
		int m40 = 0;
		int f40 = 0;
		int m50 =0;
		int f50=0;
		int m60 =0;
		int f60=0;
		int m70=0;
		int f70=0;
		int m80=0;
		int f80=0;
		
		for (int i=1; i< clientList.size();i++){
			Client client = clientList.get(i);
			if(client.getGender() != null){
				if (client.getGender().equals("Male")){
					if (client.getAge() <= 10){
						m10++;
					} else if (client.getAge() <=20){
						m20++;
					} else if (client.getAge() <= 30){
						m30++;
					} else if (client.getAge() <= 40){
						m40++;
					} else if (client.getAge() <= 50){
						m50++;
					} else if (client.getAge() <= 60){
						m60++;
					} else if (client.getAge() <= 70){
						m70++;
					} else {
						m80++;
					}
				}else {
					if (client.getAge() <= 10){
						f10++;
					} else if (client.getAge() <=20){
						f20++;
					} else if (client.getAge() <= 30){
						f30++;
					} else if (client.getAge() <= 40){
						f40++;
					} else if (client.getAge() <= 50){
						f50++;
					} else if (client.getAge() <= 60){
						f60++;
					} else if (client.getAge() <= 70){
						f70++;
					} else {
						f80++;
					}
				}
			}
			
		}
				
		ageGenderReport.add(new AgeAndGender("10",m10/(m10+f10),f10/(m10+f10)));
		ageGenderReport.add(new AgeAndGender("20",m20/(m20+f20),f20/(m20+f20)));
		ageGenderReport.add(new AgeAndGender("30",m30/(m30+f30),f30/(m30+f30)));
		ageGenderReport.add(new AgeAndGender("40",m40/(m40+f40),f40/(m40+f40)));
		ageGenderReport.add(new AgeAndGender("50",m50/(m50+f50),f50/(m50+f50)));
		ageGenderReport.add(new AgeAndGender("60",m60/(m60+f60),f60/(m60+f60)));
		ageGenderReport.add(new AgeAndGender("70",m70/(m70+f70),f70/(m70+f70)));
		ageGenderReport.add(new AgeAndGender("80",m80/(m80+f80),f80/(m80+f80)));
		
		
		
		return ageGenderReport;
	}
	
	
	public KPI getKPI(String type, String date){
		//ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type);
		int in = 0;
		int out = 0;
		
		for (int i =1; i< clientList.size(); i++){
			Client client = clientList.get(i);
			if(client.getCreatedtime().contains(date) && client.getHospitalAdmitted() != null){
				in++;
			} else if(client.getCreatedtime().contains(date) && client.getHospitalAdmitted() == null){
				out++;
			}
		}
		
		Utility utility = new Utility();
		
		String month = utility.getMonth(Integer.parseInt(date.substring(5)	));
		
		return new KPI(month+"-"+date.substring(0, 4),in,out);
	}

	
	public KPI getKPIVisa(String type, String date){
		//ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type);
		int in = 0;
		int out = 0;
		
		for (int i =1; i< clientList.size(); i++){
			Client client = clientList.get(i);
			if(client.getCreatedtime().contains(date) && client.getVisaType().contains("Indonesia")){
				in++;
			} else if(client.getCreatedtime().contains(date)){
				out++;
			}
		}
		
		
		
		return new KPI(date,in,out);
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