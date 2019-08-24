package model;

public class Proiezione {
	
	private Integer id;
	private String titolo;
	private String data;
	private String ora;
	private Integer numeroSala;
	
	public Proiezione(Integer id, String titolo, String data, String ora, Integer numeroSala) {
		this.id = id;
		this.titolo = titolo;
		this.data = data;
		this.ora = ora;
		this.numeroSala = numeroSala;
	}

	public Integer getId() {
		return id;
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

	public Integer getNumeroSala() {
		return numeroSala;
	}
	
}
