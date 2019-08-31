package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import controller.Controller;
import model.PoltronaInProiezione;

public class ToolBar extends JToolBar {
	
	private JButton bookingButton;
	private TableDialog tableDialog;
	private JButton plusButton;
	private PrenotazioneEmailDialog emailDialog;
	
	public ToolBar(JFrame parent, Controller controller, ReservationPanel resPanel) {
		
		bookingButton = new JButton(Utils.createImage("/images/icons8-book-25.png"));
		plusButton = new JButton(Utils.createImage("/images/icons8-plus-25.png"));
		
		plusButton.setToolTipText("New Booking");
		bookingButton.setToolTipText("Booking Panel");
		
		tableDialog = new TableDialog(parent);
		emailDialog = new PrenotazioneEmailDialog(controller);
		
		bookingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableDialog.setData(controller.getPoltronaList());
				tableDialog.refresh();
				tableDialog.setVisible(true);
			}
		});
		
		tableDialog.setBookingTableListener(new BookingTableListener() {
			@Override
			public void rowDeleted(int codice) {
				try {
					controller.deletePosto(codice, resPanel.getProEvent());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				resPanel.remPosti();
				resPanel.lowerLayout();
			}
		});
		
		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emailDialog.setVisible(true);
			}
		});
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(bookingButton);
		add(plusButton);
	}
	
	public void setData(List<PoltronaInProiezione> db) {
		tableDialog.setData(db);
	}
}
