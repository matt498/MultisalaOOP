package gui;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtility {

	public static void sendMail(String recipient) throws MessagingException {
		System.out.println("Sending email...");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		String myEmail = "cinemaOOP@gmail.com";
		String password = "kanhuT-dokpiv-3budju";

		Session session = Session.getInstance(props, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, password);
			}
		});

		Message message = prepareMessage(session, myEmail, recipient);
		
		Transport.send(message);
		System.out.println("Message sent");

	}

	private static Message prepareMessage(Session session, String myEmail, String recipient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Biglietto Victoria");
			message.setText("prova progetto OOP");
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		} 
		
		return null;
		
	}
}
