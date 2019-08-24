package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class FilmBarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> filmCombo;
	private JLabel filmLabel;
	private JComboBox<String> oraCombo;
	private JLabel oraLabel;
	private JComboBox<Integer> salaCombo;
	private JLabel salaLabel;
	private SalaListener salaListener;
	private ProiezioneEvent proEvent;

	public FilmBarPanel(Controller controller) {

		proEvent = new ProiezioneEvent();

		/////////////// FILM COMBO AND LABEL //////////////

		filmCombo = new JComboBox<String>();
		Dimension dim = filmCombo.getPreferredSize();
		dim.width = 100;
		filmCombo.setPreferredSize(dim);

		// set up combo//
		FilmComboModel filmModel = new FilmComboModel(controller);
		filmCombo.setModel(filmModel);
		filmCombo.setSelectedIndex(-1);

		// adding action listener to combo
		filmCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proEvent.setTitolo((String) ((JComboBox<?>) e.getSource()).getSelectedItem());
				oraCombo.setModel(
						new OraComboModel(controller, proEvent));
				oraCombo.setSelectedIndex(-1);
			}
		});

		filmLabel = new JLabel("Film:");

		/////////////// ORA COMBO AND LABEL //////////////

		oraCombo = new JComboBox<String>();
		oraCombo.setPreferredSize(dim);

		// adding action listener to oracombo per trovare le sale del film selezionato
		oraCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proEvent.setOra((String) ((JComboBox<?>) e.getSource()).getSelectedItem());
				salaCombo.setModel(
						new SalaComboModel(controller, proEvent));
				salaCombo.setSelectedIndex(-1);
			}
		});

		oraLabel = new JLabel("Ora:");

		/////////////// SALA COMBO AND LABEL //////////////

		salaCombo = new JComboBox<Integer>();
		salaCombo.setPreferredSize(dim);
		salaCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (salaListener != null) {
					if (salaCombo.getSelectedIndex() != -1) {
						proEvent.setNumeroSala((int) ((JComboBox<?>) e.getSource()).getSelectedItem());
						salaListener.salaActionPerformed(proEvent);
					}
				}

			}
		});

		salaLabel = new JLabel("Sala:");

		setLayout(new FlowLayout(FlowLayout.LEFT));

		setBorder(BorderFactory.createEtchedBorder());

		add(filmLabel);
		add(filmCombo);
		add(oraLabel);
		add(oraCombo);
		add(salaLabel);
		add(salaCombo);

	}

	public void setSalaListener(SalaListener salaListener) {
		this.salaListener = salaListener;
	}

}
