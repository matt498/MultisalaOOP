package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.PoltronaInProiezione;

public class TableDialog extends JDialog {

	private JTable table;
	private BookingTableModel tableModel;
	private JPopupMenu popup;
	private BookingTableListener bookingTableListener;
	private JButton cancelButton;

	public TableDialog(JFrame parent) {
		super(parent, "Booking", false);

		tableModel = new BookingTableModel();
		table = new JTable();
		popup = new JPopupMenu();
		cancelButton = new JButton("Cancel");

		table.setRowHeight(25);

		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
					;
				}
			}

		});

		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (bookingTableListener != null) {
					bookingTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		table.setModel(tableModel);

		setLayout(new BorderLayout());

		add(new JScrollPane(table), BorderLayout.CENTER);
		add(new JPanel().add(cancelButton), BorderLayout.SOUTH);

	}
	
	public void setData(List<PoltronaInProiezione> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setPersonTableListener(BookingTableListener listener) {
		this.bookingTableListener = listener;
	}
}
