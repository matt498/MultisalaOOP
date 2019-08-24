package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import gui.ProiezioneEvent;

public class Database {

	private Sala sala;
	private List<Proiezione> proList;
	private Connection con;

	private int port;
	private String user;
	private String password;

	public Database() {
		sala = new Sala();
		proList = new ArrayList<Proiezione>();
	}

	public int getSeat() {
		return proList.size();
	}
	
	public Sala getSala() {
		return sala;
	}

	public void configure(int port, String user, String password) throws Exception {
		this.port = port;
		this.user = user;
		this.password = password;

		if (con != null) {
			disconnect();
			connect();
		}
	}

	public void connect() throws Exception {

		if (con != null) {
			return;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:3306/multisala?useSSL=false";
		con = DriverManager.getConnection(url, "root", "password");

		System.out.println("Connected: " + con);

	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
				System.out.println("Connection to database closeddd");
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}

	/// DA SISTEMARE ///

	public void loadSala(ProiezioneEvent proEvent) throws SQLException {
		sala.getSeatList().clear();
		sala.setId(proEvent.getNumeroSala());
		PreparedStatement checkStmt = null;
		String sql = null;

		try {
			connect();
			sql = "select numero from poltrona where nome=? and username ='victoria'";
			checkStmt = con.prepareStatement(sql);
			checkStmt.setInt(1, proEvent.getNumeroSala());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet results = checkStmt.executeQuery();

		while (results.next()) {
			int numero = results.getInt("numero");

			sala.getSeatList().add(new Seat(numero, proEvent.getNumeroSala(), false));
		}

		sql = "select numero from poltrona_in_proiezione where id=? and data_=? and ora=? and nome=? and username='victoria'";
		PreparedStatement postiCheck = con.prepareStatement(sql);

		int idFilm = 0;
		String data = null;
		for (Proiezione proiezione : proList) {
			if ((proiezione.getTitolo().equals(proEvent.getTitolo())) && (proiezione.getOra().equals(proEvent.getOra()))
					&& (proiezione.getNumeroSala() == proEvent.getNumeroSala())) {
				idFilm = proiezione.getId();
				data = proiezione.getData();
			}
		}

		postiCheck.setInt(1, idFilm);
		postiCheck.setString(2, data);
		postiCheck.setString(3, proEvent.getOra());
		postiCheck.setInt(4, proEvent.getNumeroSala());

		results.close();
		results = postiCheck.executeQuery();

		while (results.next()) {
			int numero = results.getInt("numero");
			ListIterator<Seat> it = sala.getSeatList().listIterator();
			while (it.hasNext()) {
				Seat seat = it.next();
				if (seat.getNumero() == numero) {
					seat.setOccupato(true);
					it.set(seat);
				}
			}
		}

		results.close();
		checkStmt.close();
		
		for(Seat seat: sala.getSeatList()) {
			System.out.println(seat.getNumero() + " " + seat.getOccupato());
		}
	}

	public void loadProiezioni() throws SQLException {
		proList.clear();

		// domani la data sarà sbagliata
		String sql = "select p.*, f.titolo from proiezione p join film f where username='victoria' and p.id=f.id and p.data=current_date()";
		PreparedStatement selectStmt = con.prepareStatement(sql);

		ResultSet results = selectStmt.executeQuery();

		while (results.next()) {
			int id = results.getInt("id");
			String data = results.getDate("data").toString();
			String ora = results.getTime("ora").toString();
			String titolo = results.getString("titolo");
			int numeroSala = results.getInt("nome");

			Proiezione proiezione = new Proiezione(id, titolo, data, ora, numeroSala);
			proList.add(proiezione);

		}
		results.close();
		selectStmt.close();

	}

	/*
	 * public void insertPoltrone() throws Exception { connect();
	 * 
	 * String sql = "insert into poltrona values (?, 1, 'victoria')";
	 * PreparedStatement insertStmt = con.prepareStatement(sql);
	 * 
	 * for(int i=2; i<=150; i++) { insertStmt.setInt(1, i); insertStmt.execute(); }
	 * 
	 * insertStmt.close(); con.close(); disconnect(); }
	 */

	public List<Proiezione> getProiezione() {
		return this.proList;
	}

}
