package ui_package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import DataPackage.ProductItem;
import logic.HarborLogic;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class LoadShipWindow extends JFrame {

	private ArrayList<ProductItem> loadingItems;

	ProductItem currentItem;
	SpinnerNumberModel spinModel;

	public LoadShipWindow(HarborLogic hLogic) {

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
		for (ProductItem item : hLogic.getHarbor().getStock()) {
			itemsComboBox.addItem(item.getName());
		}

		selectItemPanel.add(itemsComboBox);
		selectItemPanel.add(Box.createRigidArea(new Dimension(30, 0)));

		JSpinner itemSpinner = new JSpinner();
		selectItemPanel.add(itemSpinner);

		contentPanel.add(selectItemPanel);
		
		setContentPane(contentPanel);
		
		itemsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentItem = hLogic.findItem((String) itemsComboBox.getSelectedItem());
				if (currentItem != null) {
					spinModel = new SpinnerNumberModel(0, 0, currentItem.getCount(), 1);
					itemSpinner.setModel(spinModel);
				}
			}
		});

	}

}
