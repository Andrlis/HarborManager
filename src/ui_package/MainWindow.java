package ui_package;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import DataPackage.Harbor;

import org.apache.log4j.Logger;

public class MainWindow extends JFrame {
	
	public static final Logger logger = Logger.getLogger(MainWindow.class);
	
	public MainWindow(Harbor harbor) {
		super("HarborManager");

		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("anchor.PNG");
		setIconImage(icon.getImage());
		
		logger.info("Main window open.");
		
		/*
		 * Main horizontal content box.
		 */
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout((new BoxLayout(contentPanel, BoxLayout.X_AXIS)));
		
		/*
		 * Panel with piers` information. 
		 */
		JPanel pierPanel = new JPanel();
		pierPanel.setLayout(new BoxLayout(pierPanel, BoxLayout.Y_AXIS));

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
		
		pierPanel.add(new JLabel("Choose a pier:"));
		pierPanel.add(tabPane);
		pierPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		pierPanel.setPreferredSize(new Dimension(400,400));
		
		/*
		 * Panel with stock`s goods information. 
		 */
		JPanel stockPanel = new JPanel();
		stockPanel.setLayout(new BoxLayout(stockPanel,BoxLayout.Y_AXIS));
				
		stockPanel.add(new JLabel("Stock:"));
		
		AbstractTableModel tModel = new StockTable(harbor.getStock());
		JTable stockTable = new JTable(tModel);
		stockTable.setPreferredScrollableViewportSize(new Dimension(350, 300));
		stockTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(stockTable);
		stockPanel.add(stockScroll);
		
		stockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton stockRefresh = new JButton("Refresh Stock");
		stockPanel.add(stockRefresh);
		stockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		/*
		 * Add piers and stock panel to horizontal content panel.
		 */
		contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		contentPanel.add(pierPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		contentPanel.add(stockPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		setContentPane(contentPanel);
	}

	private static JPanel makePanel() {
		JPanel p = new JPanel();
		
		return p;
	}

}