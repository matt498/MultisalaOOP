package Cinema;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Image image;

    public ImagePanel() {
       try {                
          image = ImageIO.read(new File("/Users/matteoferrari/Desktop/sala.png"));
          image = image.getScaledInstance(1214, 517, Image.SCALE_DEFAULT);
       } catch (IOException ex) {
            // handle exception...
    	   ex.printStackTrace();
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}