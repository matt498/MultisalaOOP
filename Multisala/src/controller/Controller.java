package controller;

import java.sql.SQLException;
import java.util.List;

import gui.EmailBookingEvent;
import gui.ProiezioneEvent;
import model.Database;
import model.PoltronaInProiezione;
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
	
	public int loadRidotti(ProiezioneEvent proEvent) throws SQLException {
		return db.loadRidotti(proEvent);
	}
	
	public int loadPrenotati(ProiezioneEvent proEvent) throws SQLException {
		return db.loadPrenotati(proEvent);
	}
	
	public int loadCapienza(ProiezioneEvent proEvent) throws SQLException {
		return db.loadCapienza(proEvent);
	}
	
	public void checkOutTickets(ProiezioneEvent proEvent) throws SQLException {
		db.checkOutTickets(proEvent);
	}
	
	public List<PoltronaInProiezione> getPoltronaList() {
		return db.getPoltronaList();
	}
	
	public void loadPoltroneProiezione(ProiezioneEvent proEvent) throws SQLException {
		db.loadPoltroneProiezione(proEvent);
	}

	public void deletePosto(int codice, ProiezioneEvent proEvent) throws SQLException {
		db.deletePosto(codice, proEvent);
	}
	
	public void loadSpecificProiezione(String data) throws SQLException {
		db.loadSpecificProiezione(data);
	}
	
	public List<Proiezione> getSpecList() {
		return db.getSpecList();
	}
	
	public void loadSpecPosti(String titolo, String ora, String data, int numeroSala) throws SQLException {
		db.loadSpecPosti(titolo, ora, data, numeroSala);
	}
	
	public List<Seat> getSpecPostiList() {
		return db.getSpecPostiList();
	}
	
	public void checkOutPrenotazione(int numero, EmailBookingEvent e) throws SQLException {
		db.checkOutPrenotazione(numero, e);
	}
	
	public List<Integer> getCodici(int size) throws SQLException {
		return db.getCodici(size);
	}
}
