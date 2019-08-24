package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controller.Controller;

public class BookingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FilmBarPanel filmBarPanel;
	private ReservationPanel reservationPanel;
	private CheckOutPanel checkOutPanel;

	public BookingPanel(Controller controller) {

		setLayout(new BorderLayout());

		filmBarPanel = new FilmBarPanel(controller);
		reservationPanel = new ReservationPanel(controller, this.filmBarPanel);
		checkOutPanel = new CheckOutPanel();
		
		add(filmBarPanel, BorderLayout.NORTH);
		add(reservationPanel, BorderLayout.CENTER);
		add(checkOutPanel, BorderLayout.EAST);

	}

	public CheckOutPanel getCheckOutPanel() {
		return checkOutPanel;
	}
	
}
