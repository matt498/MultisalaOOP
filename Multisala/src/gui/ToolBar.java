package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	
	private JButton bookingButton;
	
	public ToolBar() {
		
		bookingButton = new JButton(Utils.createImage("/images/icons8-book-16.png"));
		bookingButton.setToolTipText("Booking Panel");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(bookingButton);
	}
}
