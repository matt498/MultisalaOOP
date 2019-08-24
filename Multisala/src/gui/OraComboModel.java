package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import controller.Controller;
import model.Proiezione;

public class OraComboModel extends DefaultComboBoxModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Proiezione> proList;
	private Controller ctrl;

	public OraComboModel(Controller controller, ProiezioneEvent proEvent) {
		proList = new ArrayList<Proiezione>();
		ctrl = controller;

		proList.addAll(ctrl.getProiezione());
		
		for (Proiezione proiezione : proList) {
			if (proiezione.getTitolo().equals(proEvent.getTitolo())) {
				String ora = (String) proiezione.getOra();
				addElement(ora);
			}
		}
	}

}
