package ui_package;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import DataPackage.Harbor;
import DataPackage.Pier;
import DataPackage.Stock;
import logic.HarborLogic;

public class PierPanel extends JPanel {

	Pier pier;
	JButton loadShip;
	JLabel shipName;
	JLabel shipCountry;
	JLabel shipCount;
	JLabel shipWeight;
	StockTable stock_table;
	AbstractTableModel tModel;
	LoadShipWindow loadWindow;

	public PierPanel(Pier pier, Harbor harbor) {
		this.pier = pier;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel shipDataPanel = new JPanel();
		GridLayout grid = new GridLayout(0, 2);
		grid.setHgap(20);
		grid.setVgap(5);
		shipDataPanel.setLayout(grid);

		shipDataPanel.add(new JLabel("Ship`s name:"));
		shipName = new JLabel(pier.getShip().getName());
		shipDataPanel.add(shipName);

		shipDataPanel.add(new JLabel("Ship`s country:"));
		shipCountry = new JLabel(pier.getShip().getCountry());
		shipDataPanel.add(shipCountry);

		shipDataPanel.add(new JLabel("Ship`s goods count:"));
		shipCount = new JLabel(String.valueOf(this.pier.getShip().getGoods().size()));
		shipDataPanel.add(shipCount);

		shipDataPanel.add(new JLabel("Ship`s goods max weight:"));
		shipWeight = new JLabel(String.valueOf(pier.getShip().getMaxWeight()));
		shipDataPanel.add(shipWeight);
		this.add(shipDataPanel);

		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		stock_table = new StockTable(this.pier.getShip().getGoods());
		tModel = stock_table;
		JTable stockTable = new JTable(tModel);
		stockTable.setPreferredScrollableViewportSize(new Dimension(300, 120));
		stockTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(stockTable);
		this.add(stockScroll);

		this.add(Box.createRigidArea(new Dimension(0, 5)));

		loadShip = new JButton("Load ship");
		loadShip.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(loadShip);
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		loadShip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				  SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                new LoadShipWindow(harbor, pier);
			                pier.pause();
			            }
			        });
			}
		});
	}

	/**
	 * Обновление панели
	 */
	public void updatePanel() {
		if (this.pier.getShip() != null) {
			shipName.setText(this.pier.getShip().getName());
			shipCountry.setText(this.pier.getShip().getCountry());
			shipCount.setText(String.valueOf(this.pier.getShip().getGoods().size()));
			shipWeight.setText(String.valueOf(this.pier.getShip().getMaxWeight()));
			stock_table.setStock(this.pier.getShip().getGoods());
		}
	}

	/**
	 * Сброс таблицы в исходное состояние.
	 */
	public void resetPanel() {
		shipName.setText("No name");
		shipCountry.setText("No country");
		shipCount.setText("0");
		shipWeight.setText("0");
	}

	/**
	 * Обновление таблицы с товаром
	 */
	public void updateTable() {
		tModel.fireTableDataChanged();
	}

	public JPanel setPanel() {
		return this;
	}

}
