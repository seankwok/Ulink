package ulink.main;

import java.util.ArrayList;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection connection = new DatabaseConnection();
		
		ArrayList<Client> clientList = connection.retrieveAllClient();
		
		System.out.print(clientList.get(0).getClientName());
	}

}
