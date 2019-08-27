package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import controller.Controller;
import model.Sala;
import model.Seat;

public class ReservationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToggleButton interoButton;
	private JToggleButton ridottoButton;
	private ButtonGroup bigliettoGroup;
	private JLabel interiLabel;
	private JTextField interiField;
	private JLabel ridottiLabel;
	private JTextField ridottiField;
	private JLabel prenotatiLabel;
	private JTextField prenotatiField;
	private JLabel capienzaLabel;
	private JTextField capienzaField;
	private JPanel upperPanel;
	private JPanel lowerPanel;
	private Controller controller;
	private FilmBarPanel filmBarPanel;
	private RefreshTotale refreshTotale;
	private ProiezioneEvent proEvent;
	private Boolean intero;

	public ReservationPanel(Controller controller, FilmBarPanel filmBarPanel, ToolBar toolBar) {

		setLayout(new BorderLayout());

		this.controller = controller;
		this.filmBarPanel = filmBarPanel;
		
		intero = new Boolean(true);

		interoButton = new JToggleButton("INTERO");
		ridottoButton = new JToggleButton("RIDOTTO");
		bigliettoGroup = new ButtonGroup();
		
		interoButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					intero = true;
				}
			}
		});
		
		ridottoButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					intero = false;
				}
			}
		});

		interiLabel = new JLabel("Interi: ");
		ridottiLabel = new JLabel("Ridotti: ");
		prenotatiLabel = new JLabel("Prenotati: ");
		capienzaLabel = new JLabel("Capienza: ");

		interiField = new JTextField(5);
		interiField.setEditable(false);
		ridottiField = new JTextField(5);
		ridottiField.setEditable(false);
		prenotatiField = new JTextField(5);
		prenotatiField.setEditable(false);
		capienzaField = new JTextField(5);
		capienzaField.setEditable(false);

		bigliettoGroup.add(interoButton);
		bigliettoGroup.add(ridottoButton);

		interoButton.setSelected(true);

		upperPanel = new JPanel();
		upperlayout();

		lowerPanel = new JPanel();

		setBorder(BorderFactory.createTitledBorder("Reservation"));

		add(upperPanel, BorderLayout.NORTH);
		add(new JScrollPane(lowerPanel), BorderLayout.CENTER);

		this.filmBarPanel.setSalaListener(new SalaListener() {
			@Override
			public void salaActionPerformed(ProiezioneEvent proEvent) {
				System.out.println(proEvent.getNumeroSala());
				ReservationPanel.this.proEvent = proEvent;
				try {
					controller.loadSala(proEvent);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					controller.loadPoltroneProiezione(proEvent);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//setto il conteggio degli interi
				try {
					interiField.setText(new Integer(controller.loadInteri(proEvent)).toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//setto il conteggio dei ridotti
				try {
					ridottiField.setText(new Integer(controller.loadRidotti(proEvent)).toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//setto il conteggio dei prenotati
				try {
					prenotatiField.setText(new Integer(controller.loadPrenotati(proEvent)).toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//setto capienza
				try {
					capienzaField.setText(new Integer(controller.loadCapienza(proEvent)).toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				remPosti();
				lowerLayout();
			}
		});
	}
	
	
	public JTextField getInteriField() {
		return interiField;
	}


	public JTextField getRidottiField() {
		return ridottiField;
	}


	public JTextField getPrenotatiField() {
		return prenotatiField;
	}


	public JTextField getCapienzaField() {
		return capienzaField;
	}


	public FilmBarPanel getFilmBar() {
		return filmBarPanel;
	}
	
	public ProiezioneEvent getProEvent() {
		return proEvent;
	}
	
	public void setListener(RefreshTotale refreshTotale) {
		this.refreshTotale = refreshTotale;
	}
	
	public void remPosti() {
		//Get the components in the panel
		Component[] componentList = lowerPanel.getComponents();

		//Loop through the components
		for(Component c : componentList){

		    //Find the components you want to remove
		    if(c instanceof SeatButton  || c instanceof JLabel){

		        //Remove it
		        this.lowerPanel.remove(c);
		    }
		}
	}

	public void lowerLayout() {

		Sala sala = controller.getSala();

		lowerPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;

		if (sala.getId() == 1 || sala.getId()== 2) {

			int cont = 0;
			char x='A';
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 15; j++, cont++) {
					
					if(j == 0) {
						lowerPanel.add(new JLabel(String.format("%c:", x)), gc);
						gc.gridx++;
						x++;
					}
					
					Seat seat = sala.getSeatList().get(cont);
					SeatButton seatButton = new SeatButton(seat.getNumero(), seat.getOccupato(), this, this.controller); // mettere a posto
																									// l'indice
					seatButton.setEnabled(!(seat.getOccupato()));
					if(seat.getOccupato()) {
						seatButton.setIcon(Utils.createImage("/images/icons8-person-20.png"));
					}
					else {
						seatButton.setIcon(Utils.createImage("/images/icons8-filled-circle-20.png"));
					}
					lowerPanel.add(seatButton, gc);
					gc.gridx++;
				}
				gc.gridy++;
				gc.gridx = 0;
			}
		}
		
		this.lowerPanel.revalidate();
		this.lowerPanel.repaint();
	}

	private void upperlayout() {
		GridBagConstraints gc = new GridBagConstraints();

		// first element //
		gc.gridx = 0;
		gc.gridy = 0;

		Dimension dim = ridottoButton.getPreferredSize();
		interoButton.setPreferredSize(dim);

		upperPanel.add(interoButton, gc);

		// second element //
		gc.gridy++;

		upperPanel.add(ridottoButton, gc);

		// third element //
		gc.gridy++;

		upperPanel.add(interiLabel, gc);

		gc.gridy++;

		upperPanel.add(interiField, gc);

		// fourth element //
		gc.gridy++;

		upperPanel.add(ridottiLabel, gc);

		gc.gridy++;

		upperPanel.add(ridottiField, gc);

		// fifth element //
		gc.gridy++;

		upperPanel.add(prenotatiLabel, gc);

		gc.gridy++;

		upperPanel.add(prenotatiField, gc);

		// sixth element //
		gc.gridy++;

		upperPanel.add(capienzaLabel, gc);

		gc.gridy++;

		upperPanel.add(capienzaField, gc);
	}
	
	public Boolean getIntero() {
		return intero;
	}
	
	public RefreshTotale getListener() {
		return this.refreshTotale;
	}
}

class SeatButton extends JToggleButton {
	private Integer numero;
	private Boolean occupato;

	public SeatButton(Integer numero, Boolean occupato, ReservationPanel resPanel, Controller controller) {
		this.numero = numero;
		this.occupato = occupato;

		setText(String.format("%s", numero.toString()));

		setSelected(occupato);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean status = isSelected();
				Boolean intero = resPanel.getIntero();
				controller.addTicket(numero, status, intero);
				Integer totale = controller.getTotale();
				if(resPanel.getListener() != null) {
					resPanel.getListener().refreshTot(totale);
				}
			}
		});
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getOccupato() {
		return occupato;
	}

	public void setOccupato(Boolean occupato) {
		this.occupato = occupato;
	}

};
