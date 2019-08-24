package model;

import java.sql.SQLException;

import controller.Controller;

public class prova {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller db = new Controller();
		
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.loadFilm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Proiezione proiezione: db.getProiezione()) {
			System.out.println(proiezione.getTitolo());
		}
		
		db.disconnect();
	}

}
