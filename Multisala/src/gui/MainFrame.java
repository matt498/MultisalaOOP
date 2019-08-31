package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import controller.Controller;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8026416994513756565L;

	private BookingPanel bookingPanel;
	private Controller controller;
	private ToolBar toolBar;
	
	public MainFrame() {
		super("Multisala");
		
		UIManager.put("ToggleButton.disabledText", Color.RED);

		controller = new Controller();
		bookingPanel = new BookingPanel(controller, toolBar);
		toolBar = new ToolBar(this, controller, bookingPanel.getResPanel());

		setLayout(new BorderLayout());

		setJMenuBar(createMenuBar());

		add(toolBar, BorderLayout.NORTH);
		add(bookingPanel, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.disconnect();
				dispose();
				System.gc();
				System.exit(0);
			}

		});

		setMinimumSize(new Dimension(500, 400));
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// creazione della Barra dei menu /////

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("CheckOut");
		showFormItem.setSelected(true);
		
		showFormItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(showFormItem.isSelected()) {
					bookingPanel.getCheckOutPanel().setVisible(true);
				}
				else {
					bookingPanel.getCheckOutPanel().setVisible(false);
				}
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();

					for (WindowListener listener : listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}

				}
			}
		});
		
		exitItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		return menuBar;
	}

}
