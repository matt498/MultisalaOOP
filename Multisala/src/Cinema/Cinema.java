package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class Cinema extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar;
	private JMenuItem openFile;
	private JMenuItem closeFile;
	private JMenuItem newFile;
	private JMenu file;
	private JLabel leftPanelTitle;
	private JToggleButton leftPanelTitle1;
	private JToggleButton leftPanelTitle2;
	private JToggleButton leftPanelTitle3;
	private JPanel p2Right;
	
	public Cinema() throws HeadlessException {
		super("MultiSala");
		
		newFile = new JMenuItem("New");						//Settaggi Menu
		openFile = new JMenuItem("Open");
		closeFile = new JMenuItem("Close");
		file = new JMenu("File");
		menubar = new JMenuBar();
		file.add(newFile);
		file.add(openFile);
		file.add(closeFile);
		menubar.add(file);
		menubar.setOpaque(true);		//non Funziona
		menubar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menubar);							//Fine menu
		
		leftPanelTitle = new JLabel("MENU");
		Font font = new Font("Helvetica Neue", Font.BOLD,  15);
		leftPanelTitle.setFont(font);
		leftPanelTitle.setForeground(Color.WHITE); //must change color
		
		leftPanelTitle1 = new JToggleButton("Biglietteria");
		leftPanelTitle1.setFont(font);
		leftPanelTitle1.setForeground(Color.WHITE); //must change color
		leftPanelTitle1.setFocusPainted(false);
		leftPanelTitle1.setBackground(Color.DARK_GRAY);
		leftPanelTitle1.setMaximumSize(new Dimension(100,20));
		leftPanelTitle1.addActionListener(this);
		
		leftPanelTitle2 = new JToggleButton("Prenotazioni");
		leftPanelTitle2.setFont(font);
		leftPanelTitle2.setForeground(Color.WHITE); //must change color
		leftPanelTitle2.setFocusPainted(false);
		leftPanelTitle2.setBackground(Color.DARK_GRAY);
		leftPanelTitle2.setMaximumSize(new Dimension(100,20));
		leftPanelTitle2.addActionListener(this);
		
		leftPanelTitle3 = new JToggleButton("Fatture");
		leftPanelTitle3.setFont(font);
		leftPanelTitle3.setForeground(Color.WHITE); //must change color
		leftPanelTitle3.setFocusPainted(false);
		leftPanelTitle3.setBackground(Color.DARK_GRAY);
		leftPanelTitle3.setMaximumSize(new Dimension(100,20));
		leftPanelTitle3.addActionListener(this);
		
		ButtonGroup group = new ButtonGroup();
		group.add(leftPanelTitle1);
		group.add(leftPanelTitle2);
		group.add(leftPanelTitle3);

		JPanel p2Left = new JPanel();
		BoxLayout  boxLayout  = new BoxLayout(p2Left, BoxLayout.Y_AXIS);
		p2Left.setLayout(boxLayout);
		p2Left.setBackground(Color.DARK_GRAY);
		p2Left.setPreferredSize(new Dimension(100,400));
		p2Left.add(leftPanelTitle);
		p2Left.add(leftPanelTitle1);
		p2Left.add(leftPanelTitle2);
		p2Left.add(leftPanelTitle3);
		
		JPanel panelROSSO = new JPanel();
		JPanel panelVERDE = new JPanel();
		panelROSSO.setBackground(Color.RED);
		panelVERDE.setBackground(Color.GREEN);
		
		HomePanel home = new HomePanel();
		
		CardLayout cardLayout = new CardLayout();
		p2Right = new JPanel(cardLayout);
		p2Right.add(home, "HOME");
		p2Right.add(panelROSSO, "ROSSO");
		p2Right.setBackground(new Color(0,0,0,100));
		p2Right.setPreferredSize(new Dimension(300,400));
		
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().add(BorderLayout.LINE_START, p2Left);
		getContentPane().add(BorderLayout.CENTER, p2Right);
		/*
		 attaccare tutti i componenti
		 */
		
		//add(p1);											//Fine settaggio pannelli
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1500, 850);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
	                System.setProperty("apple.laf.useScreenMenuBar", "true");
	                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                new Cinema();
	            }
	            catch(ClassNotFoundException e) {
	                System.out.println("ClassNotFoundException: " + e.getMessage());
	            }
	            catch(InstantiationException e) {
	                System.out.println("InstantiationException: " + e.getMessage());
	            }
	            catch(IllegalAccessException e) {
	                System.out.println("IllegalAccessException: " + e.getMessage());
	            }
	            catch(UnsupportedLookAndFeelException e) {
	                System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
	            }

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == leftPanelTitle1) {
			CardLayout cardLayout = (CardLayout) p2Right.getLayout();
			cardLayout.show(p2Right, "HOME");
		}
		if (e.getSource() == leftPanelTitle2) {
			CardLayout cardLayout = (CardLayout) p2Right.getLayout();
			cardLayout.show(p2Right, "ROSSO");
		}
	}

}
