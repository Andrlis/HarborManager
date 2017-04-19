package DataPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Harbor {

	private Pier[] piers;
	private volatile Stock stock;
	private volatile ArrayList<Ship> shipQueue;

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
	
	/**
	 * ��������� ������� �� �������.
	 */
	synchronized public Ship getShipFromQueue(){
		if (this.getShipQueue().isEmpty() == true) {
			return null;
		} else {
			Ship ship = this.getShipQueue().get(0);
			this.getShipQueue().remove(0);
			System.out.println("SHIP: " + ship.getName());
			return ship;
		}
	}
	
	/**
	 * ��������� �������
	 */
	synchronized public void unloadShip(ProductItem item){
		boolean isExist = false;
		
		System.out.println("ITEM: " + item.getName());
		for (int i=0;i<stock.getGoods().size();i++) {
			if (item.getName().equals(stock.getGoods().get(i).getName())) {
				isExist = true;
				stock.getGoods().get(i).incCount(item.getCount());
			}
		}
		if (isExist == false)
			this.setGoodsItem(item);
	}
	
	/**
	 * ����� ���������� � ��������� ����� � �������.
	 */
	public String toString(){
		String harborStatus;
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		harborStatus = formatter.format(now)+" Harbor. Items` count: "+this.stock.getCount()+"; Ships in queue: "+this.shipQueue.size()+";";
		for(int i = 0; i<piers.length;i++)
			harborStatus += " Pier #"+(i+1)+piers[i].toString()+";";
		return harborStatus;
	}
	
	/**
	 * ������ ������� �� ����������
	 */
	public void startThreads(){
		for (int i =0; i<piers.length;i++)
			piers[i].start();
	}
	
	/**
	 * ���������� ������ �������
	 */
	public void finishThreds(){
		for (int i =0; i<piers.length;i++)
			piers[i].interrupt();
		for (int i =0; i<piers.length;i++){
			try{
				piers[i].join();
			}catch(InterruptedException e){	}
		}
	}
}
