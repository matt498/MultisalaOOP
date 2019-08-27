package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.PoltronaInProiezione;

public class BookingTableModel extends AbstractTableModel {

	private List<PoltronaInProiezione> db;
	
	private String[] colNames = { "Numero", "Codice", "Film", "Ora", "Sala"};
	
	public BookingTableModel() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return db.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rows, int cols) {
		PoltronaInProiezione seat = db.get(rows);

		switch (cols) {
		case 0:
			return seat.getNumero();
		case 1:
			return seat.getCodice();
		case 2:
			return seat.getTitolo();
		case 3:
			return seat.getOra();
		case 4:
			return seat.getSala();
		}

		return null;
	}

	public void setData(List<PoltronaInProiezione> db) {
		// TODO Auto-generated method stub
		this.db = db;
	}

}
