package logic;

import DataPackage.Harbor;
import DataPackage.ProductItem;
import data_base.ShipDAO;
import data_base.StockDAO;

public class HarborLogic {
	
	Harbor harbor;
	ShipDAO ship_dao;
	StockDAO stock_dao;
	
	public HarborLogic(int numOfPorts){
		stock_dao = new StockDAO();
		ship_dao = new ShipDAO();
		
		ship_dao.getConnection();
		stock_dao.getConnection();
		
		harbor = new Harbor(numOfPorts);
		this.harbor.setStock(stock_dao.select());
	}
		
	/**
	 * Load ship queue from data base
	 */
	public void loadShipQueue(){
		this.harbor.getShipQueue().addAll(ship_dao.select());
	}

	public Harbor getHarbor(){
		return this.harbor;
	}
	
	public void start(){
		this.harbor.startThreads();
	}
	
	public void finish(){
		this.harbor.finishThreds();
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.ship_dao.closeConnection();
		this.stock_dao.closeConnection();
		super.finalize();
	}
	
}
