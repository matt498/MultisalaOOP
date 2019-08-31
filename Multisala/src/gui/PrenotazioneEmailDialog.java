package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.Controller;
import model.Proiezione;

public class PrenotazioneEmailDialog extends JDialog {

	private JTable table;
	private EmailTableModel tableModel;
	private JDateChooser dateChooser;
	private JButton loadButton;
	private JLabel emailLabel;
	private JTextField emailField;
	private JButton cancelButton;

	public PrenotazioneEmailDialog(Controller controller) {

		table = new JTable();
		tableModel = new EmailTableModel();
		table.setModel(tableModel);

		dateChooser = new JDateChooser();
		loadButton = new JButton("Load");
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField(15);
		cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooser.getDate());

				try {
					controller.loadSpecificProiezione(date);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				setData(controller.getSpecList());
				refresh();
			}
		});

		table.setRowHeight(25);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.NORTH);
		layoutComponent();
		
		pack();
	}

	private void layoutComponent() {

		JPanel p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		//////// First line ////////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		p1.add(dateChooser, gc);

		gc.gridx = 1;
		p1.add(loadButton, gc);

		//////// next line ////////
		gc.gridx = 0;
		gc.gridy++;
		p1.add(emailLabel, gc);

		gc.gridx++;
		p1.add(emailField, gc);

		////// Last line //////
		gc.weighty = 0.5;
		gc.gridy++;
		gc.gridx = 0;
		p1.add(cancelButton, gc);

		add(p1, BorderLayout.CENTER);
	}

	public void setData(List<Proiezione> proList) {
		tableModel.setData(proList);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

}
