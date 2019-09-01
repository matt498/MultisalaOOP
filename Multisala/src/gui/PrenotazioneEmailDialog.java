package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import controller.Controller;
import model.Proiezione;

public class PrenotazioneEmailDialog extends JDialog {

	private JTable table;
	private EmailTableModel tableModel;
	private JDateChooser dateChooser;
	private JButton loadButton;
	private JTextArea textArea;
	private JCheckBox checkBox;
	private JList<Integer> postoList;
	private JLabel emailLabel;
	private JTextField emailField;
	private JButton okButton;
	private JButton cancelButton;
	private ListModel listModel;

	public PrenotazioneEmailDialog(Controller controller) {

		table = new JTable();
		tableModel = new EmailTableModel();
		table.setModel(tableModel);

		dateChooser = new JDateChooser();
		loadButton = new JButton("Load");
		textArea = new JTextArea();
		checkBox = new JCheckBox("Seat: ");
		postoList = new JList<Integer>();
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField(15);
		okButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		listModel = new ListModel();
		
		/// set up postList
		postoList.setModel(listModel);
		postoList.setVisibleRowCount(10);
		postoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		postoList.setLayoutOrientation(JList.VERTICAL);
		
		checkBox.setEnabled(false);
		postoList.setEnabled(false);
		emailField.setEnabled(false);
		okButton.setEnabled(false);

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		loadButton.setPreferredSize(cancelButton.getPreferredSize());
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
				checkBox.setEnabled(true);
			}
		});
		
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					postoList.setEnabled(checkBox.isSelected());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(dateChooser.getDate());
					String titolo = (String) table.getValueAt(row, 0);
					System.out.println(titolo);
					String ora = (String) table.getValueAt(row, 1);
					int numeroSala = (Integer) table.getValueAt(row, 2);
					
					try {
						controller.loadSpecPosti(titolo, ora, date, numeroSala);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					listModel.setData(controller.getSpecPostiList());
					listModel.refresh();
				}
				
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
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		p1.add(dateChooser, gc);

		///////// Next Line ////////
		gc.gridx = 1;
		gc.gridy = 1;
		p1.add(loadButton, gc);

		///////// Next Line ////////
		gc.gridx = 1;
		gc.gridy++;
		p1.add(textArea, gc);

		///////// Next Line ////////
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		p1.add(checkBox, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		JScrollPane listScroller = new JScrollPane(postoList);
		Dimension dim = emailField.getPreferredSize();
		dim.height = 140;
		listScroller.setPreferredSize(dim);
		p1.add(listScroller, gc);

		//////// next line ////////
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		p1.add(emailLabel, gc);

		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		p1.add(emailField, gc);

		///////// Next Line ////////
		gc.gridx = 1;
		gc.gridy++;
		p1.add(okButton, gc);

		////// Last line //////
		gc.weighty = 0.5;
		gc.gridy++;
		gc.gridx = 1;
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
