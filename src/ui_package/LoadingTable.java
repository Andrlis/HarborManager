package ui_package;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataPackage.ProductItem;

public class LoadingTable extends AbstractTableModel{
ArrayList<ProductItem> stock;
	
	public void setStock(ArrayList<ProductItem> stock){
		this.stock = stock;
	}
	
	LoadingTable(ArrayList<ProductItem> stock){
		super();
		this.stock = stock;
	}
	
	@Override
	public int getRowCount(){
		return stock.size();
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
			result = "Count";
			break;
		}
		return result;
	}
	
	@Override 
	public Object getValueAt(int row, int column){
		switch(column){
		case 0:
			return stock.get(row).getName();
		case 1: 
			return stock.get(row).getCount();
			default:
				return "";
		}
	}

}
