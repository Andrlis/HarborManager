package ui_package;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import logic.HarborLogic;

import org.apache.log4j.Logger;

import DataPackage.Ship;

public class MainWindow extends JFrame {
	
	HarborLogic hLogic;
	private ShipsWindow shipsWindow;
	private ShipsWindow oceanWinow;
	
	public static final Logger logger = Logger.getLogger(MainWindow.class);
	
	public MainWindow(HarborLogic harborLogic) {
		super("HarborManager");
		hLogic = harborLogic;
		shipsWindow = new ShipsWindow(hLogic.getHarbor().getShipQueue());
		oceanWinow = new ShipsWindow(hLogic.getHarbor().getOcean());
		
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("anchor.PNG");
		setIconImage(icon.getImage());
		
		logger.info("Main window open.");
		
		/*
		 * Menu.
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu shipsMenu = new JMenu("Ships");
		
		JMenuItem shipsQueueMenu = new JMenuItem("Show queue");
		shipsMenu.add(shipsQueueMenu);
		
		JMenuItem oceanMenu = new JMenuItem("Ocean queue");
		shipsMenu.add(oceanMenu);
		
		shipsMenu.getAccessibleContext().setAccessibleDescription("Ships queue.");
		menuBar.add(shipsMenu);
		
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
		
		JScrollPane scroll_1 = new JScrollPane(harborLogic.getHarbor().getPier(0).getPanel());
		scroll_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tabPane.addTab("Pier1",scroll_1);
		
		JScrollPane scroll_2 = new JScrollPane(harborLogic.getHarbor().getPier(1).getPanel());
		scroll_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tabPane.addTab("Pier2",scroll_2);

		JScrollPane scroll_3 = new JScrollPane(harborLogic.getHarbor().getPier(2).getPanel());
		scroll_3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tabPane.addTab("Pier3",scroll_3);
		
		JScrollPane scroll_4 = new JScrollPane(harborLogic.getHarbor().getPier(3).getPanel());
		scroll_4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tabPane.addTab("Pier4",scroll_4);
		
		pierPanel.add(new JLabel("Choose a pier:"));
		pierPanel.add(tabPane);
		pierPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		pierPanel.setPreferredSize(new Dimension(400,400));
		
		/*
		 * Panel with stock`s goods information. 
		 */
		JPanel stockPanel = new JPanel();
		stockPanel.setLayout(new BoxLayout(stockPanel,BoxLayout.Y_AXIS));
				
		stockPanel.add(new JLabel("Stock:"),JComponent.LEFT_ALIGNMENT);
		
		AbstractTableModel tModel = new StockTable(harborLogic.getHarbor().getStock());
		JTable stockTable = new JTable(tModel);
		stockTable.setPreferredScrollableViewportSize(new Dimension(350, 300));
		stockTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(stockTable);
		stockPanel.add(stockScroll);
		
		stockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton stockRefresh = new JButton("Refresh Stock");
		stockRefresh.setAlignmentX(JComponent.CENTER_ALIGNMENT);
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
		
		setJMenuBar(menuBar);
		setContentPane(contentPanel);
		
		stockRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	tModel.fireTableDataChanged();
            }
        });
		
		shipsQueueMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				shipsWindow.setVisible(true);
			}
		});
		
		oceanMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				oceanWinow.setVisible(true);
			}
		});
	}
}