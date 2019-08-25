package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.Controller;

public class CheckOutPanel extends JPanel {

	private static final long serialVersionUID = -2377632156178279527L;
	
	private JLabel totLabel;
	private JTextField totField;
	private JLabel scontoLabel;
	private JTextField scontoField;
	private JButton okButton;
	private JButton cancelButton;
	private ReservationPanel resPanel;
	
	public CheckOutPanel(ReservationPanel resPanel, Controller controller) {
		
		totLabel = new JLabel("Totale: ");
		totField = new JTextField(10);
		scontoLabel = new JLabel("Sconto: ");
		scontoField = new JTextField(10);
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		this.resPanel = resPanel;
		
		this.resPanel.setListener(new RefreshTotale() {
			
			@Override
			public void refreshTot(Integer totale) {
				totField.setText(totale.toString());
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				totField.setText(new Integer(0).toString());
				controller.deleteOrder();
				resPanel.lowerLayout();
			}
		});
		
		panelLayout();
		
		Border innerBorder = BorderFactory.createTitledBorder("CheckOut");
		Border outerBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
	}

	private void panelLayout() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		/// First Row ///
		
		add(totLabel, gc);
		
		gc.gridx++;
		add(totField, gc);
		
		/// Second Row ///
		
		gc.gridy++;
		gc.gridx=0;
		
		add(scontoLabel, gc);
		
		gc.gridx = 1;
		add(scontoField, gc);
		
		/// Third Row ///
		
		gc.gridy++;
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 0, 0, 0);
		
		Dimension dim = cancelButton.getPreferredSize();
		okButton.setPreferredSize(dim);
		
		add(okButton, gc);
		
		/// Fourth Row ///
		
		gc.gridy++;
		gc.insets = new Insets(0, 0, 0, 0);
		
		add(cancelButton, gc);
				
	}
	
}
