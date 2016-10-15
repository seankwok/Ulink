package ulink.main;

import java.util.ArrayList;
import java.util.HashMap;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		
		ArrayList<Client> clientList = connection.retrieveAllClient();
		
		TopK test = new TopK();
		
		HashMap<String,Integer>test1 = test.topReferral();
		
		System.out.print(test1.toString());
	}

}
