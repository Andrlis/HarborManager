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
		for (int i= 0; i<numPiers;i++){
			piers[i]= new Pier(this);
		}
		stock = new Stock();
		shipQueue = new ArrayList<Ship>();
	}

	public Pier getPier(int number){
		return this.piers[number];
	}
	
	public ArrayList<Ship> getShipQueue(){
		return shipQueue;
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
		
	public void setShipToQueue(Ship ship){
		this.shipQueue.add(ship);
	}
	
	public String toString(){
		String harborStatus;
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		harborStatus = formatter.format(now)+" Harbor. Items` count: "+this.stock.getCount()+"; Ships in queue: "+this.shipQueue.size()+";";
		for(int i = 0; i<piers.length;i++)
			harborStatus += " Pier #"+(i+1)+piers[i].toString()+";";
		return harborStatus;
	}
}
