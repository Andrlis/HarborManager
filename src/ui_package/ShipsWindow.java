package ui_package;

import DataPackage.Ship;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ShipsWindow extends JFrame {
	ArrayList<Ship> shipsList;

	public ShipsWindow(ArrayList<Ship> ships) {
		super("Ships");
		this.shipsList = ships;

		setSize(400, 300);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		ImageIcon icon = new ImageIcon("anchor.PNG");
		setIconImage(icon.getImage());

		/*
		 * Main horizontal content box.
		 */
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout((new BoxLayout(contentPanel, BoxLayout.Y_AXIS)));

		AbstractTableModel tModel = new ShipTable(shipsList);
		JTable shipsTable = new JTable(tModel);
		shipsTable.setPreferredScrollableViewportSize(new Dimension(400, 250));
		shipsTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(shipsTable);

		contentPanel.add(stockScroll);

		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		JButton refresh = new JButton("Refresh");
		contentPanel.add(refresh);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		setContentPane(contentPanel);
		
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tModel.fireTableDataChanged();
			}
		});
	}
}
