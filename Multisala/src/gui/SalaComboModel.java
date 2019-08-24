package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import controller.Controller;
import model.Proiezione;

public class SalaComboModel extends DefaultComboBoxModel<Integer>{
	
	private static final long serialVersionUID = 1L;
	private List<Proiezione> proList;
	private Controller ctrl;
	
	public SalaComboModel(Controller controller, ProiezioneEvent proEvent) {
		proList = new ArrayList<Proiezione>();
		ctrl = controller;

		proList.addAll(ctrl.getProiezione());
		
		for (Proiezione proiezione : proList) {
			if (proiezione.getOra().equals(proEvent.getOra())) {
				int sala = proiezione.getNumeroSala();
				addElement(sala);
			}
		}

	}
}
