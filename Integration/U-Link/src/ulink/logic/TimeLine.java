package ulink.logic;

import java.util.*;


import ulink.constructor.*;
import ulink.dao.DatabaseConnection;

public class TimeLine {

	public void sendEmail() {

		DatabaseConnection database = new DatabaseConnection();

		ArrayList<Timeline> timelineList = database.getTimeline();
		ArrayList<Condition> conditionList = database.retrieveAllCondition();

		for (int i = 0; i < timelineList.size(); i++) {
			Timeline timeline = timelineList.get(i);
			int temp = timeline.getYearToSend();
			if (temp > 0) {
				temp -= 1;
				database.editTimeline(timeline.getID(), temp);
			} else if (temp == 0) {
				// send email
				for (int x = 0; x < conditionList.size(); x++) {
					Condition condition = conditionList.get(x);
					if (timeline.getConditionName().equals(condition.getConditionName())) {
						database.editTimeline(timeline.getID(), condition.getYears());
					}
				}
			}
		}

	}

	public HashMap<String, Integer> displayAllScreening() {
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<Condition> conditionList = database.retrieveAllCondition();
		HashMap<String, Integer> screeningList = new HashMap<String, Integer>();
		
		
		for (int i = 0; i < conditionList.size(); i++) {
			Condition condition = conditionList.get(i);
			screeningList.put(condition.getScreening(), condition.getAgeRequired());
		}
		return screeningList;
	}

	public void addScreeningToTimeline() {
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<Client> clientList = database.retrieveAllClientList();
		ArrayList<Timeline> timelineList = database.getTimeline();
		ArrayList<Condition> conditionList = database.retrieveAllCondition();
		Utility utility = new Utility();
		
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			String dob = client.getDateOfBirth();
			int year = Integer.parseInt(dob.substring(0, 4));
			
			int age = utility.getAge(year);
			System.out.println(year);

			for (int x = 0; x < conditionList.size(); x++) {
				boolean check = true;
				Condition condition = conditionList.get(x);
				for (int y = 0; y< timelineList.size(); y++) {
						if(condition.getConditionName().equals(timelineList.get(y).getConditionName())){
							check = false;
						}
					}
				if (condition.getAgeRequired() < age && check) {
					database.addTimeline(condition.getConditionName(), client.getPassportNumber(),
							condition.getYears());
				}
			}
		}
	}



}
