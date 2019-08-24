package Cinema;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class PostiToggleButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int[][] fModel;
    private final int fX;
    private final int fY;
    ImageIcon icona1 = new ImageIcon("/Users/matteoferrari/Desktop/seatIcon.png");
    ImageIcon icona2 = new ImageIcon("/Users/matteoferrari/Desktop/seatIcon2.png");
    
    public PostiToggleButton(final int x, final int y, final int[][] model) {
        fX= x;
        fY= y;
        fModel= model;
        setIcon((icona1));
        setRolloverIcon((icona1));
        setPressedIcon(icona2);
        setDisabledIcon(icona1);
        setBorderPainted(false);
        setBorder(null);
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
        //setContentAreaFilled(false);
        setBackground(Color.BLACK);
  
        addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (isSelected()) {
                	setIcon(icona2);
                	fModel[fX][fY] = 1;
                } else {
                	setIcon(icona1);
                	fModel[fX][fY] = 0;
                }
            }
        });
        /*
        setBorderPainted(false);
        setBorder(null);
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setIcon(icona1);
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if( fModel[fX][fY] == 0) {
            		fModel[fX][fY] = 1;
            		setIcon(icona2);
            	}
            	if( fModel[fX][fY] == 1) {
            		fModel[fX][fY] = 0;
            		setIcon(icona1);
            	}
            }
        }); */
   
    }

}
