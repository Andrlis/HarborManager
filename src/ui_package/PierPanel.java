package ui_package;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import DataPackage.Pier;

public class PierPanel extends JPanel {
		
	Pier pier;
	JLabel shipName;
	JLabel shipCountry;
	JLabel shipCount;
	JLabel shipWeight;
	
	public PierPanel(Pier pier){
		
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
		
		AbstractTableModel tModel = new StockTable(this.pier.getShip().getGoods());
		JTable stockTable = new JTable(tModel);
		stockTable.setPreferredScrollableViewportSize(new Dimension(300, 200));
		stockTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane stockScroll = new JScrollPane(stockTable);
		this.add(stockScroll);
		
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton loadShip = new JButton("Load ship");
		JButton unloadShip = new JButton("Unload ship");
		
		buttonPanel.add(loadShip);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(unloadShip);
	
			
		this.add(Box.createRigidArea(new Dimension(0, 10)));				
	}
	
	public void setPier(Pier pier){
		this.pier = pier;
		shipName.setText(this.pier.getShip().getName());
		shipCountry.setText(this.pier.getShip().getCountry());
		shipCount.setText(String.valueOf(this.pier.getShip().getGoods().size()));
		shipWeight.setText(String.valueOf(this.pier.getShip().getMaxWeight()));
	}
	
	public JPanel setPanel(){
		return this;
	}

}
