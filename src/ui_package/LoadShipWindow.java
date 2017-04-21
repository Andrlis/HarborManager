package ui_package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import DataPackage.Harbor;
import DataPackage.Pier;
import DataPackage.ProductItem;
import logic.HarborLogic;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

public class LoadShipWindow extends JFrame {

	private ArrayList<ProductItem> loadingItems = new ArrayList<ProductItem>();

	ProductItem currentItem;
	SpinnerNumberModel spinModel;

	public LoadShipWindow(Harbor harbor, Pier pier) {

		super("Ship`s goods loading");

		setSize(500, 300);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		ImageIcon icon = new ImageIcon("anchor.PNG");
		setIconImage(icon.getImage());

		/*
		 * Main horizontal content box.
		 */
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout((new BoxLayout(contentPanel, BoxLayout.Y_AXIS)));

		/*
		 * Elements for selecting items.
		 */
		JPanel selectItemPanel = new JPanel();
		selectItemPanel.setLayout(new BoxLayout(selectItemPanel, BoxLayout.X_AXIS));

		JComboBox<String> itemsComboBox = new JComboBox<String>();
		for (ProductItem item : harbor.getStock()) {
			itemsComboBox.addItem(item.getName());
		}

		contentPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		selectItemPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		selectItemPanel.add(itemsComboBox);
		selectItemPanel.add(Box.createRigidArea(new Dimension(20, 0)));

		JSpinner itemSpinner = new JSpinner();
		selectItemPanel.add(itemSpinner);
		selectItemPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		JButton addBtn = new JButton("Add");
		selectItemPanel.add(addBtn);
		selectItemPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		selectItemPanel.setMaximumSize(new Dimension(500, 30));
		contentPanel.add(selectItemPanel);
		
		LoadingTable items_table = new LoadingTable(loadingItems);
		AbstractTableModel tModel = items_table;
		JTable itemsTable = new JTable(tModel);
		itemsTable.setPreferredScrollableViewportSize(new Dimension(300, 90));
		itemsTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(itemsTable);
		
		contentPanel.add(Box.createRigidArea(new Dimension(0,10)));
		contentPanel.add(stockScroll);
		
		JButton loadBtn = new JButton("Load");
		loadBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		contentPanel.add(Box.createRigidArea(new Dimension(0,10)));
		contentPanel.add(loadBtn);
		contentPanel.add(Box.createRigidArea(new Dimension(0,10)));

		setContentPane(contentPanel);
		
		setVisible(true);

		itemsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentItem = harbor.findItem((String) itemsComboBox.getSelectedItem());
				if (currentItem != null) {
					spinModel = new SpinnerNumberModel(0, 0, currentItem.getCount(), 1);
					itemSpinner.setModel(spinModel);
				}
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProductItem item = new ProductItem(currentItem.getName(), (int)itemSpinner.getValue());
				item.setWeight(currentItem.getWeight());
				loadingItems.add(item);
				currentItem.decCount((int)itemSpinner.getValue());
				tModel.fireTableDataChanged();
			}
		});

		loadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TO-DO loading ship method
				pier.play();
				setVisible(false);
			}
		});

	}
}
