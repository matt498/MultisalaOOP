package controller;

import java.sql.SQLException;
import java.util.List;

import gui.ProiezioneEvent;
import model.Database;
import model.Proiezione;
import model.Sala;
import model.Seat;

public class Controller {

	
	Database db = new Database();
	
	public int getSeat () {
		return db.getSeat();
	}
	
	/*public void loadSala() throws SQLException {
		db.loadSala();
	}*/
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void configure(int port, String user, String password) throws Exception {
		db.configure(port, user, password);
	}
	
	public void loadFilm() throws SQLException {
		db.loadProiezioni();
	}
	
	public void loadSala(ProiezioneEvent proEvent) throws SQLException {
		db.loadSala(proEvent);
	}
	
	public List<Proiezione> getProiezione() {
		return db.getProiezione();
	}
	
	public Sala getSala() {
		return db.getSala();
	}
	
	public void addTicket(int numero, Boolean occupato, Boolean intero) {
		db.addTicket(numero, occupato, intero);
	}
	
	public int getTotale() {
		return db.getTotale();
	}
	
	public void deleteOrder() {
		db.deleteOrder();
	}
	
	public int loadInteri(ProiezioneEvent proEvent) throws SQLException {
		return db.loadInteri(proEvent);
	}
}
