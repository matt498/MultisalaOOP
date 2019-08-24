package Cinema;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class SalaMatrixPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dim=10;
	int matrix[][]= new int[10][10];
	
	public SalaMatrixPanel() {
		setLayout(new GridLayout(dim,dim));
		setBackground(Color.BLACK);
		
		for(int r = 0; r < dim; r++){
	        for(int c = 0; c < dim; c++){
	        	PostiToggleButton button= new PostiToggleButton(r, c, matrix);
	            add(button);
	        }
	    }
	}
	
	public int getMatrixValues() {	//per vedere quanti posti occupati
		return 0;
		
	}
		
}
