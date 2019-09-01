package gui;

public class EmailBookingEvent {
	String titolo;
	String data;
	String ora;
	int numeroSala;
	
	public EmailBookingEvent(String titolo, String data, String ora, int numeroSala) {
		this.titolo = titolo;
		this.data = data;
		this.ora = ora;
		this.numeroSala = numeroSala;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getData() {
		return data;
	}

	public String getOra() {
		return ora;
	}

	public int getNumeroSala() {
		return numeroSala;
	}
	
	
	
}
