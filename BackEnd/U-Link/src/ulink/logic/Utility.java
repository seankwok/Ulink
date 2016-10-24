package ulink.logic;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utility {

		public boolean sendMail(String emailTo, String emailFrom, String host){

			// Get system properties
		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);

		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);

		      try {
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(emailFrom));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

		         // Set Subject: header field
		         message.setSubject("This is the Subject Line!");

		         // Now set the actual message
		         message.setText("This is actual message");

		         // Send message
		         Transport.send(message);
		         return true;
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		         return false;
		      }
			
			
		}
}
