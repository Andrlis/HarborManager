package DataPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Harbor {

	private Pier[] piers;
	private Stock stock;
	private ArrayList<Ship> shipQueue;

	public Harbor(int numPiers) {
		piers = new Pier[numPiers];
		stock = new Stock();
		shipQueue = new ArrayList<Ship>();
	}

	public String getPierStatus(int number) {
		return this.piers[number].getStatus();
	}

	public void setPierStatus(int number, String status) {
		this.piers[number].setStatus(status);
	}

	public boolean getPierAvailable(int number) {
		return this.piers[number].getAvailable();
	}

	public void setPierAvailable(int number, boolean available) {
		this.piers[number].setAvailable(available);
	}

	public Ship getShipFromPier(int number) {
		return this.piers[number].getShip();
	}

	public void setShipToPier(int number, Ship ship) {
		this.piers[number].setShip(ship);
	}

	public ArrayList<ProductItem> getStock() {
		return this.stock.getGoods();
	}

	public void setStock(ArrayList<ProductItem> stock) {
		this.stock.setGoods(stock);
	}

	public void setGoodsItem(ProductItem item) {
		this.stock.setGoodsItem(item);
	}

	public int getStockItemsCount() {
		return this.stock.getCount();
	}

	public void incStockItem(int inc){
		this.stock.incCount(inc);
	}
	
	public void decStockItem(int dec){
		this.stock.decCount(dec);
	}
	
	public void incItemCount(int inc, ProductItem item){
		this.stock.incItemCount(inc, item);
	}
	
	public void decItemCount(int dec, ProductItem item){
		this.stock.decItemCount(dec, item);
	}
	
	public void setShipToQueue(Ship ship){
		this.shipQueue.add(ship);
	}
	
	public Ship getShipFromQueue(){
		Ship ship = this.shipQueue.get(0);
		this.shipQueue.remove(0);
		return ship;
	}
	
	public String toString(){
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(now)+" Harbor. Items` count: "+this.stock.getCount()+"; Ships in queue: "+this.shipQueue.size()+";\n";
	}
}
