package gui;

import javax.mail.MessagingException;

/*
 * MAIN di prova per inviare una mail
 */

public class ProvaMail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MailUtility.sendMail("matteferrari.85@gmail.com");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
