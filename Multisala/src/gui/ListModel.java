package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import model.Seat;

public class ListModel extends AbstractListModel<Integer> {
	
	public ListModel() {
		// TODO Auto-generated constructor stub
		postiList = new ArrayList<Seat>();
	}
	
	private List<Seat> postiList;

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return postiList.size();
	}

	@Override
	public Integer getElementAt(int index) {
		// TODO Auto-generated method stub
		return postiList.get(index).getNumero();
	}
	
	public void setData(List<Seat> postiList) {
		this.postiList = postiList;
	}

	public void refresh() {
		super.fireContentsChanged(this, 0, postiList.size());
	}
	
}
