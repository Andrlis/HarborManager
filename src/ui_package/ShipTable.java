package ui_package;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataPackage.Ship;

public class ShipTable extends AbstractTableModel {
	
	ArrayList<Ship> ships;
	
	public void setStock(ArrayList<Ship> ships){
		this.ships = ships;
	}
	
	ShipTable(ArrayList<Ship> ships){
		super();
		this.ships = ships;
	}
	
	@Override
	public int getRowCount(){
		return ships.size();
	}
	
	@Override
	public int getColumnCount(){
		return 2;
	}
	
	@Override 
	public String getColumnName(int c){
		String result = "";
		switch(c){
		case 0:
			result = "Name";
			break;
		case 1: 
			result = "Country";
			break;
		}
		return result;
	}
	
	@Override 
	public Object getValueAt(int row, int column){
		switch(column){
		case 0:
			return ships.get(row).getName();
		case 1:
			return ships.get(row).getCountry();
		default:
				return "";
		}
	}
}
