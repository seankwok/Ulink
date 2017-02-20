package ulink.logic;

import java.text.DecimalFormat;
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
		double m10 = 0;
		double f10 = 0;
		double m20 = 0;
		double f20 = 0;
		double m30 = 0;
		double f30 = 0;
		double m40 = 0;
		double f40 = 0;
		double m50 = 0;
		double f50 = 0;
		double m60 = 0;
		double f60 = 0;
		double m70 = 0;
		double f70 = 0;
		double m80 = 0;
		double f80 = 0;
		double m90 = 0;
		double f90 = 0;
		
		

		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (client.getGender() != null) {
				if (client.getGender().equals("Male") && client.getAge() != -1) {
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
					} else if (client.getAge() <= 80) {
						m80++;
					} else {
						m90++;
					}
				} else if (client.getGender().equals("Female") && client.getAge() != -1) {
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
					} else if (client.getAge() <= 80) {
						f80++;
					} else {
						f90++;
					}
				}
			}

		}

		double total10 = 0;
		double total20 = 0;
		double total30 = 0;
		double total40 = 0;
		double total50 = 0;
		double total60 = 0;
		double total70 = 0;
		double total80 = 0;
		double total90 = 0;
		double sumOfClient = m10 + m20 + m30 + m40+ m50 + m60+ m70 +m80+ m90 +f10+f20+f30+f40+f50+f60+f70+f80+f90;
		
		
		if (clientList.size() != 0) {
			System.out.println(clientList.size());
			total10 = 1.0 * (m10 + f10) / sumOfClient * 100;
			total20 = 1.0 * (m20 + f20) / sumOfClient * 100;
			total30 = 1.0 * (m30 + f30) / sumOfClient * 100;
			total40 = 1.0 * (m40 + f40) / sumOfClient * 100;
			total50 = 1.0 * (m50 + f50) / sumOfClient * 100;
			total60 = 1.0 * (m60 + f60) / sumOfClient * 100;
			total70 = 1.0 * (m70 + f70) / sumOfClient * 100;
			total80 = 1.0 * (m80 + f80) / sumOfClient * 100;
			total90 = 1.0 * (m90 + f90) / sumOfClient * 100;
		}

		double totalm10 = 0;
		double totalm20 = 0;
		double totalm30 = 0;
		double totalm40 = 0;
		double totalm50 = 0;
		double totalm60 = 0;
		double totalm70 = 0;
		double totalm80 = 0;
		double totalm90 = 0;

		if (m10 + f10 != 0) {
			totalm10 = 1.0 * m10 / (m10 + f10) * 100;
		}

		if (m20 + f20 != 0) {
			totalm20 = 1.0 * m20 / (m20 + f20) * 100;
		}

		if (m30 + f30 != 0) {
			totalm30 = 1.0 * m30 / (m30 + f30) * 100;
		}

		if (m40 + f40 != 0) {
			totalm40 = 1.0 * m40 / (m40 + f40) * 100;
		}

		if (m50 + f50 != 0) {
			totalm50 = 1.0 * m50 / (m50 + f50) * 100;
		}

		if (m60 + f60 != 0) {
			totalm60 = 1.0 * m60 / (m60 + f60) * 100;
		}

		if (m70 + f70 != 0) {
			totalm70 = 1.0 * m70 / (m70 + f70) * 100;
		}

		if (m80 + f80 != 0) {
			totalm80 = 1.0 * m80 / (m80 + f80) * 100;
		}
		if (m90 + f90 != 0) {
			totalm90 = 1.0 * m90 / (m90 + f90) * 100;
		}

		double totalf10 = 0;
		double totalf20 = 0;
		double totalf30 = 0;
		double totalf40 = 0;
		double totalf50 = 0;
		double totalf60 = 0;
		double totalf70 = 0;
		double totalf80 = 0;
		double totalf90 = 0;

		if (m10 + f10 != 0) {
			totalf10 = 1.0 * f10 / (m10 + f10) * 100;
		}

		if (m20 + f20 != 0) {
			totalf20 = 1.0 * f20 / (m20 + f20) * 100;
		}

		if (m30 + f30 != 0) {
			totalf30 = 1.0 * f30 / (m30 + f30) * 100;
		}

		if (m40 + f40 != 0) {
			totalf40 = 1.0 * f40 / (m40 + f40) * 100;
		}

		if (m50 + f50 != 0) {
			totalf50 = 1.0 * f50 / (m50 + f50) * 100;
		}

		if (m60 + f60 != 0) {
			totalf60 = 1.0 * f60 / (m60 + f60) * 100;
		}

		if (m70 + f70 != 0) {
			totalf70 = 1.0 * f70 / (m70 + f70) * 100;
		}

		if (m80 + f80 != 0) {
			totalf80 = 1.0 * f80 / (m80 + f80) * 100;
		}
		if (m90 + f90 != 0) {
			totalf90 = 1.0 * f90 / (m90 + f90) * 100;
		}
		DecimalFormat two = new DecimalFormat("0.00");

		ageGenderReport.add(new AgeAndGender("10", Double.parseDouble(two.format(totalm10)),
				Double.parseDouble(two.format(totalf10)), Double.parseDouble(two.format(total10))));
		ageGenderReport.add(new AgeAndGender("20", Double.parseDouble(two.format(totalm20)),
				Double.parseDouble(two.format(totalf20)), Double.parseDouble(two.format(total20))));
		ageGenderReport.add(new AgeAndGender("30", Double.parseDouble(two.format(totalm30)),
				Double.parseDouble(two.format(totalf30)), Double.parseDouble(two.format(total30))));
		ageGenderReport.add(new AgeAndGender("40", Double.parseDouble(two.format(totalm40)),
				Double.parseDouble(two.format(totalf40)), Double.parseDouble(two.format(total40))));
		ageGenderReport.add(new AgeAndGender("50", Double.parseDouble(two.format(totalm50)),
				Double.parseDouble(two.format(totalf50)), Double.parseDouble(two.format(total50))));
		ageGenderReport.add(new AgeAndGender("60", Double.parseDouble(two.format(totalm60)),
				Double.parseDouble(two.format(totalf60)), Double.parseDouble(two.format(total60))));
		ageGenderReport.add(new AgeAndGender("70", Double.parseDouble(two.format(totalm70)),
				Double.parseDouble(two.format(totalf70)), Double.parseDouble(two.format(total70))));
		ageGenderReport.add(new AgeAndGender("80", Double.parseDouble(two.format(totalm80)),
				Double.parseDouble(two.format(totalf80)), Double.parseDouble(two.format(total80))));
		ageGenderReport.add(new AgeAndGender("90", Double.parseDouble(two.format(totalm90)),
				Double.parseDouble(two.format(totalf90)), Double.parseDouble(two.format(total90))));
		return ageGenderReport;
	}

	public KPI getKPI(String type, String startDate, String endDate) {
		// ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type, startDate, endDate);
		int in = 0;
		int out = 0;

		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (client.getHospitalAdmitted().length() > 0 ) {
				in++;
			} else if (client.getHospitalAdmitted().length() == 0) {
				out++;
			}
		}

		Utility utility = new Utility();
		int temp;
		try {
		 temp = Integer.parseInt(startDate.substring(5,7));
		} catch (NumberFormatException e){
			temp = Integer.parseInt(startDate.substring(5,6));
		}
		
		String month = utility.getMonth(temp);

		return new KPI(month + "-" + startDate.substring(0, 4), in, out);
	}

	public KPI getKPIVisa(String type, String startDate, String endDate) {
		// ArrayList<KPI> kpiList = new ArrayList<>();
		ArrayList<Client> clientList = connection.retrieveAllClientByType(type, startDate, endDate);
		int in = 0;
		int out = 0;

		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			//System.out.println(client.getVisaType());
			if (client.getVisaType().contains("Indonesia")) {
				in++;
			} else {
				out++;
			}
		}

		Utility utility = new Utility();
		int temp;
		try {
		 temp = Integer.parseInt(startDate.substring(5,7));
		} catch (NumberFormatException e){
			temp = Integer.parseInt(startDate.substring(5,6));
		}
		
		String month = utility.getMonth(temp);

		return new KPI(month + "-" + startDate.substring(0, 4), in, out);
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