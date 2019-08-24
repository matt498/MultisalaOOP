package Cinema;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HomePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTable filmTable;
	JTable dataTable;
	JTable oraTable;


	public HomePanel() {
		super();
		// TODO Auto-generated constructor stub
		
		setLayout(new GridLayout(2,1));
		
		String column[]= {"Titolo"};
		String titoli[][]= {
				{"Avengers-Endgame"}, {"Spiderman-Far from home"}, {"John W."}
		};
		filmTable = new JTable(titoli, column);
		filmTable.setBounds(0, 0, 200, 300);
		JScrollPane sp1 = new JScrollPane(filmTable);
		
		String columnData[]= {"Data"};
		String data[][]= {
				{"11-07-19"}
		};
		dataTable = new JTable(data, columnData);
		dataTable.setBounds(30, 40, 200, 300);
		JScrollPane sp2 = new JScrollPane(dataTable);
		
		String columnOra[]= {"Ora"};
		String ora[][]= {
				{"22:55"}
		};
		oraTable = new JTable(ora, columnOra);
		//oraTable.setBounds(30, 40, 200, 300);
		JScrollPane sp3 = new JScrollPane(oraTable);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 3));
		p1.add(sp1);
		p1.add(sp2);
		p1.add(sp3);
		
		add(p1);   //Fine pannello per tabelle in alto
		
		
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1,2));
		
		p2.add(new SalaMatrixPanel());
		p2.add(new ConfirmPanel());
		
		add(p2);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
