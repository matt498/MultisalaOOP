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
import java.util.Set;

import gui.ProiezioneEvent;

public class Database {

	private Sala sala;
	private List<Proiezione> proList;
	private Connection con;
	private Order order;

	private int port;
	private String user;
	private String password;

	public Database() {
		sala = new Sala();
		proList = new ArrayList<Proiezione>();
		order = new Order();
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

	public int getTotale() {

		int totale = 0;
		for (Integer ticketId : order.getTicketList().keySet()) {
			int price = order.getTicketList().get(ticketId).getPrice();
			totale += price;
		}

		return totale;

	}

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

	public int loadInteri(ProiezioneEvent proEvent) throws SQLException {
		String sql = "select count(*) as cont from biglietto b\n"
				+ "join poltrona_in_proiezione p on b.codice=p.codice\n"
				+ "join (select f.id, f.titolo as titolo from film f) as film2 on film2.id=p.id\n"
				+ "where film2.titolo=? and\n" + "p.data_=current_date() and\n" + "p.ora=? and\n" + "p.nome=? and\n"
				+ "p.username='victoria' and\n" + "b.sel='intero';";

		PreparedStatement countInteriStmt = con.prepareStatement(sql);

		countInteriStmt.setString(1, proEvent.getTitolo());
		countInteriStmt.setString(2, proEvent.getOra());
		countInteriStmt.setInt(3, proEvent.getNumeroSala());

		ResultSet result = countInteriStmt.executeQuery();

		int num = 0;
		while (result.next()) {
			num = result.getInt("cont");
		}

		result.close();
		countInteriStmt.close();

		return num;
	}

	public int loadRidotti(ProiezioneEvent proEvent) throws SQLException {
		String sql = "select count(*) as cont from biglietto b\n"
				+ "join poltrona_in_proiezione p on b.codice=p.codice\n"
				+ "join (select f.id, f.titolo as titolo from film f) as film2 on film2.id=p.id\n"
				+ "where film2.titolo=? and\n" + "p.data_=current_date() and\n" + "p.ora=? and\n" + "p.nome=? and\n"
				+ "p.username='victoria' and\n" + "b.sel='ridotto'";

		PreparedStatement countRidottiStmt = con.prepareStatement(sql);

		countRidottiStmt.setString(1, proEvent.getTitolo());
		countRidottiStmt.setString(2, proEvent.getOra());
		countRidottiStmt.setInt(3, proEvent.getNumeroSala());

		ResultSet result = countRidottiStmt.executeQuery();

		int num = 0;
		while (result.next()) {
			num = result.getInt("cont");
		}

		result.close();
		countRidottiStmt.close();

		return num;

	}

	public int loadPrenotati(ProiezioneEvent proEvent) throws SQLException {
		String sql = "select count(*) as cont \n" + "from poltrona_in_proiezione p\n"
				+ "join (select f.id, f.titolo as titolo from film f) as film2 on film2.id=p.id\n"
				+ "where film2.titolo=? and\n" + "p.data_=current_date() and\n" + "p.ora=? and\n" + "p.nome=? and\n"
				+ "p.username='victoria' and \n" + "p.codice is null\n"
				+ "group by p.id,p.data_,p.ora,p.nome,p.username,p.codice";

		PreparedStatement countPrenotatiStmt = con.prepareStatement(sql);

		countPrenotatiStmt.setString(1, proEvent.getTitolo());
		countPrenotatiStmt.setString(2, proEvent.getOra());
		countPrenotatiStmt.setInt(3, proEvent.getNumeroSala());

		ResultSet result = countPrenotatiStmt.executeQuery();

		int num = 0;
		while (result.next()) {
			num = result.getInt("cont");
		}

		result.close();
		countPrenotatiStmt.close();

		return num;

	}

	public int loadCapienza(ProiezioneEvent proEvent) throws SQLException {
		String sql = "select count(*) as cont\n" + "from poltrona p1\n" + "where p1.nome=? and\n"
				+ "p1.username='victoria'\n" + "and p1.numero not in (	select numero from poltrona_in_proiezione p\n"
				+ "						join (select f.id, f.titolo as titolo from film f) as film2 on film2.id=p.id\n"
				+ "						where film2.titolo=? and\n"
				+ "                        p.username='victoria' and\n"
				+ "                        p.data_=current_date() and\n" + "                        p.ora=?)\n"
				+ "group by p1.nome, p1.username;";

		PreparedStatement countPrenotatiStmt = con.prepareStatement(sql);

		countPrenotatiStmt.setInt(1, proEvent.getNumeroSala());
		countPrenotatiStmt.setString(2, proEvent.getTitolo());
		countPrenotatiStmt.setString(3, proEvent.getOra());

		ResultSet result = countPrenotatiStmt.executeQuery();

		int num = 0;
		while (result.next()) {
			num = result.getInt("cont");
		}

		result.close();
		countPrenotatiStmt.close();

		return num;

	}

	public void addTicket(int numero, Boolean occupato, Boolean intero) {
		Seat seat = sala.getSeatList().get(numero - 1);

		Ticket ticket = new Ticket(seat);
		ticket.setId(seat.getNumero());

		if (seat.getOccupato() == false) {
			if (occupato == false) {
				order.getTicketList().remove(seat.getNumero());
			} else {
				if (intero) {
					ticket.setPrice(10);
				} else {
					ticket.setPrice(6);
				}
				order.getTicketList().put(numero, ticket);
			}
		} else {
			if (occupato == true) {
				order.getTicketList().remove(seat.getNumero());
			} else {
				if (intero) {
					ticket.setPrice(10);
				} else {
					ticket.setPrice(6);
				}
				order.getTicketList().put(numero, ticket);
			}
		}
	}

	public void checkOutTickets(ProiezioneEvent proEvent) throws SQLException {
		Set<Integer> ticketKeySet = order.getTicketList().keySet();

		String sql = "insert into biglietto (data, prezzo, sel) values (current_date(),?,?)";
		PreparedStatement insertStmt = con.prepareStatement(sql);
		
		String sql3 = "select codice from biglietto order by codice desc limit 1";
		PreparedStatement codiceStmt = con.prepareStatement(sql3);
		
		String sql2 = "insert into poltrona_in_proiezione (id, data_,ora,nome,username,numero, codice)values (\n" + 
				"(select id from film where titolo=? limit 1), \n" + 
				"current_date(),\n" + 
				"?,\n" + 
				"?,\n" + 
				"'victoria',\n" + 
				"?,\n" +
				"?\n" +
				");";
		PreparedStatement insertPoltronaStmt = con.prepareStatement(sql2);
		insertPoltronaStmt.setString(1, proEvent.getTitolo());
		insertPoltronaStmt.setString(2, proEvent.getOra());
		insertPoltronaStmt.setInt(3, proEvent.getNumeroSala());
	

		for (Integer key : ticketKeySet) {
			Ticket ticket = order.getTicketList().get(key);

			insertStmt.setInt(1, ticket.getPrice());
			if (ticket.getPrice() == 10)
				insertStmt.setString(2, "intero");
			else
				insertStmt.setString(2, "ridotto");
			
			ResultSet result = codiceStmt.executeQuery();
			
			insertStmt.executeUpdate();
			
			int codice = 0;
			while(result.next()) {
				codice = result.getInt(1);
			}
			insertPoltronaStmt.setInt(4, ticket.getSeat().getNumero());
			insertPoltronaStmt.setInt(5, codice);
			insertPoltronaStmt.executeUpdate();
			
		}
		codiceStmt.close();
		insertStmt.close();
		insertPoltronaStmt.close();
		
		order.getTicketList().clear();
		loadSala(proEvent);
		
	}

	public void deleteOrder() {
		this.order.getTicketList().clear();
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
