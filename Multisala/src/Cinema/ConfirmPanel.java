package Cinema;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfirmPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel display;
    private JComboBox<String> box;
    private String[] distros;
    private JButton annulla;
    private JButton conferma;
    
    
	public ConfirmPanel() {
		distros = new String[]{"1", "2", "3","4", "5","6", "7", "8", "9", "10"};
		box = new JComboBox<>(distros);
        display = new JLabel("Ridotti:");
        annulla= new JButton("Annulla");
        annulla.setBackground(Color.RED);
        conferma = new JButton("Conferma");
        conferma.setBackground(Color.GREEN);
        
        add(display);
        add(box);
        add(annulla);
        add(conferma);

        
	}
    
    
}
