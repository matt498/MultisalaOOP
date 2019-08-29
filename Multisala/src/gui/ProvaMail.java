package gui;

import javax.mail.MessagingException;

public class ProvaMail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MailUtility.sendMail("matteferrari.85@gmail.com", "matteferrari.85@gmail.com", "Cyber", "Scopa.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
