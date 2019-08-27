package model;

public class PoltronaInProiezione {
	private int numero;
	private int codice;
	private String titolo;
	private String ora;
	private int sala;
	
	public PoltronaInProiezione() {
		// TODO Auto-generated constructor stub
	}
	
	public PoltronaInProiezione(int numero, int codice, String titolo, String ora, int sala) {
		this.numero = numero;
		this.codice = codice;
		this.titolo = titolo;
		this.ora = ora;
		this.sala = sala;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}
	
	
}
