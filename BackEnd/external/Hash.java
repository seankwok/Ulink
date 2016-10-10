package com.extentia.training;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public String hash(String password) {
		  StringBuffer sb = new StringBuffer();
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(password.getBytes());
	        
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	      
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return sb.toString();
	       
}
}
