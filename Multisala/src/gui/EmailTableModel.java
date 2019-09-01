package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Proiezione;

public class EmailTableModel extends AbstractTableModel {
	
	private List<Proiezione> proList;
	
	private String[] colNames = {"Film", "Ora", "Sala"};
	
	public EmailTableModel() {
		// TODO Auto-generated constructor stub
		proList = new ArrayList<Proiezione>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return proList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rows, int cols) {
		// TODO Auto-generated method stub
		
		Proiezione pro = proList.get(rows);

		switch (cols) {
		case 0:
			return pro.getTitolo();
		case 1:
			return pro.getOra();
		case 2:
			return pro.getNumeroSala();
		}
		
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}
	
	public void setData(List<Proiezione> proList) {
		// TODO Auto-generated method stub
		this.proList = proList;
	}

}
