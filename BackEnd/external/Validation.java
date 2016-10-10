package com.extentia.training;

import java.util.ArrayList;

import com.extentia.DAO.DatabaseConnection;
import com.extentia.constructor.User;



public class Validation {
	public boolean loginValidatation(String username, String password){
		boolean isValid = false;
		DatabaseConnection database = new DatabaseConnection();
		Hash hash = new Hash();
		
		ArrayList<User> userList = database.getUser();
		
		for (int i=0; i <userList.size(); i++){
			User user = userList.get(i);
			String hPassword = hash.hash(password);
			if (username.toLowerCase().equals(user.getUsername().toLowerCase()) && hPassword.equals(user.getPassword())){
				isValid = true;
			}
		}
		
		return isValid;
	}
}
