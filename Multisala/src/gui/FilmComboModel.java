package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import controller.Controller;
import model.Proiezione;

public class FilmComboModel extends DefaultComboBoxModel<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Proiezione> proList;
	private Controller ctrl;
	
	public FilmComboModel(Controller controller) {
		proList = new ArrayList<Proiezione>();
		ctrl = controller;
		
		try {
			ctrl.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			ctrl.loadFilm();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		proList.addAll(ctrl.getProiezione());
		
		for(Proiezione proiezione: proList) {
			String titolo = (String) proiezione.getTitolo();
			addElement(titolo);
		}
		
	}
	
	
	
}
