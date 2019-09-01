package model;

public class Seat {

	private Integer numero;
	private Integer numeroSala;
	private Boolean occupato;

	public Seat(Integer numero, Integer numeroSala, Boolean occupato) {
		this.numero = numero;
		this.numeroSala = numeroSala;
		this.occupato = false;
	}

	public Seat(int int1) {
		// TODO Auto-generated constructor stub
		this.numero=int1;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(Integer numeroSala) {
		this.numeroSala = numeroSala;
	}

	public Boolean getOccupato() {
		return occupato;
	}

	public void setOccupato(Boolean occupato) {
		this.occupato = occupato;
	}

}
