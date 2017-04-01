package ui_package;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import DataPackage.ProductItem;;

public class MainWindow extends JFrame {
	public MainWindow() {
		super("HarborManager");

		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/*
		 * Main horizontal content box.
		 */
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout((new BoxLayout(contentPanel, BoxLayout.X_AXIS)));
		
		/*
		 * Panel with piers` information. 
		 */
		JPanel pierPanel = new JPanel();
		pierPanel.setLayout(new BorderLayout());

		JTabbedPane tabPane = new JTabbedPane();
		JPanel tab1 = makePanel();
		JPanel tab2 = makePanel();
		JPanel tab3 = makePanel();
		JPanel tab4 = makePanel();
		JPanel tab5 = makePanel();
		tabPane.addTab("Pier1", tab1);
		tabPane.addTab("Pier1", tab2);
		tabPane.addTab("Pier1", tab3);
		tabPane.addTab("Pier1", tab4);
		tabPane.addTab("Pier1", tab5);
		
		pierPanel.add(new JLabel("Choose a pier:"),BorderLayout.NORTH);
		pierPanel.add(tabPane, BorderLayout.CENTER);
		
		/*
		 * Panel with stock`s goods information. 
		 */
		JPanel stockPanel = new JPanel();
		stockPanel.setLayout(new BorderLayout());
				
		stockPanel.add(new JLabel("Stock:"),BorderLayout.NORTH);
		
		/*
		 * Add piers and stock panel to horizontal content panel.
		 */
		contentPanel.add(pierPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		contentPanel.add(stockPanel);
		
		setContentPane(contentPanel);
	}

	private static JPanel makePanel() {
		JPanel p = new JPanel();
		
		return p;
	}

}