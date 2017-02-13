package ulink.main;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.util.SystemOutLogger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
import ulink.constructor.Index;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		int ID = 48;
		DatabaseConnection connection = new DatabaseConnection();
		Condition condition = connection.retrieveAllConditionByID(ID);
		
		ArrayList<Client> clientList = connection.retrieveAllClientList();
		ArrayList<ClientByIllness> clientByIllnessList = new ArrayList<>();
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if (condition.getType().toLowerCase().equals("male") || condition.getType().toLowerCase().equals("female")) {
				if (client.getAge() >= condition.getAgeRequired() && client.getGender().toLowerCase().equals(condition.getType().toLowerCase())) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
							client.getEmail(), client.getGender(),condition.getScreening(), condition.getConditionName()));
				}
			} else {
				if (client.getAge() >= condition.getAgeRequired() / 12 && client.getGender().toLowerCase().equals(condition.getType().toLowerCase())) {
					clientByIllnessList.add(new ClientByIllness(client.getClientName(), client.getAge(),
							client.getEmail(), client.getGender(),condition.getScreening(), condition.getConditionName()));

				}
			}
		}
		
		System.out.println(clientByIllnessList.size());
	}

}
