package ulink.logic;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public boolean sendEmail(String email, String CC, String subject, String Msg, String toEmail){
		
		
		final String username = "ulinkas1";
		final String password = "lcBsK0Q]p)mt";

		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.ulinkassist.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Manager@ulinkassist.com"));
			
			message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
			message.addRecipients(Message.RecipientType.CC,
					InternetAddress.parse(CC));
			message.setSubject(subject);
			message.setContent(Msg, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
		return true;
	}
	

}
