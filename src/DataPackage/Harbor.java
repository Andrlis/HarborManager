package DataPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logic.HarborLogic;

public class Harbor {

	private HarborLogic hLogic;
	private Pier[] piers;
	private volatile Stock stock;
	private volatile ArrayList<Ship> shipQueue;
	private volatile ArrayList<Ship> ocean;

	public Harbor(int numPiers) {
		piers = new Pier[numPiers];
		for (int i= 0; i<numPiers;i++){
			piers[i]= new Pier(this);
		}
		stock = new Stock();
		shipQueue = new ArrayList<Ship>();
		ocean = new ArrayList<Ship>();
	}

	public Pier getPier(int number){
		return this.piers[number];
	}
	
	public ArrayList<Ship> getShipQueue(){
		return shipQueue;
	}
	
	public ArrayList<Ship> getOcean(){
		return ocean;
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
	 * Извлекает корабль из очереди.
	 */
	synchronized public Ship getShipFromQueue(){
		if (this.getShipQueue().isEmpty() == true) {
			return null;
		} else {
			Ship ship = this.getShipQueue().get(0);
			this.getShipQueue().remove(0);
			return ship;
		}
	}
	
	/**
	 * Добавление корабля в очередь
	 */
	synchronized public void addShipToQueue(Ship ship){
		if(ship!=null){
			this.ocean.add(ship);
		}
	}
	
	/**
	 * Разгрузка корабля
	 */
	synchronized public void unloadShip(ProductItem item){
		boolean isExist = false;
		
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
	 * Загрузка корабля
	 */
	synchronized public void loadShip(Ship ship, ArrayList<ProductItem>items){
		double itemsWeight = 0;
		
		for(ProductItem item :items){
			itemsWeight += item.getWeight()*item.getCount();
			if(itemsWeight<ship.getMaxWeight()){
				ship.setGoodsItem(item);
			}
			/*Если перегруз, то возвращаем товар на склад*/
			else{
				for (int i=0;i<stock.getGoods().size();i++) {
					if (item.getName().equals(stock.getGoods().get(i).getName())) {
						stock.getGoods().get(i).incCount(item.getCount());
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Вывод информации о состоянии порта в строуку.
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
	 * Запуск потоков на выполнение
	 */
	public void startThreads(){
		for (int i =0; i<piers.length;i++)
			piers[i].start();
	}
	
	/**
	 * Search stock item by name.
	 */
	public ProductItem findItem(String name){
		ProductItem item = null;
		for (ProductItem stockItem: stock.getGoods()){
			if(stockItem.getName().equals(name)==true){
				item = stockItem;
				break;
			}
		}
		return item;
	}
	
	/**
	 * Завершение работы потоков
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
