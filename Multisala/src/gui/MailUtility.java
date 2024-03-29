package gui;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtility implements Runnable {
	
	private String recipient;
	private List<Integer> codici;
	
	public MailUtility(String recipient, List<Integer> codici) {
		this.recipient = recipient;
		this.codici = codici;
	}

	private Message prepareMessage(Session session, String myEmail) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Biglietto Victoria");
			
			String testo = "Grazie per aver scelto il nostro cinema.\nCodici biglietti: ";
			
			for(Integer code : codici) {
				testo = testo.concat(code.toString() + "-");
			}
			
			message.setText(testo);
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		} 
		
		return null;
		
	}

	@Override
	public void run() {
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

		Message message = prepareMessage(session, myEmail);
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("Message sent");
	}
}
