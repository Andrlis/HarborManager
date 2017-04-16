package DataPackage;

import ui_package.PierPanel;


public class Pier extends Thread {

	PierPanel ui_panel;
	private String status;
	private Ship currentShip;
	private Harbor harbor;

	public Pier(Harbor harbor) {
		this.status = "Free";
		this.currentShip = new Ship();
		this.harbor = harbor;
		this.ui_panel = new PierPanel(this);
	}
	
	/**
	 * Возвращает PierPanel
	 */
	public PierPanel getPanel(){
		return this.ui_panel;
	}

	/**
	 * Изменение состояния порта.
	 */
	public void changeStatus() {
		if (this.status.equals("Free") == true)
			this.status = "Busy";
		else
			this.status = "Free";
	}

	public Ship getShip() {
		return this.currentShip;
	}

	/**
	 * Извлекает корабль из очереди.
	 */
	synchronized private void setShip() {
		if (this.harbor.getShipQueue().isEmpty() == true) {
			this.currentShip = null;
		}
		this.currentShip = this.harbor.getShipQueue().get(0);
		harbor.getShipQueue().remove(0);
		this.ui_panel.updatePanel();
	}

	/**
	 * Разгрузка корабля.
	 */
	synchronized public void unloadShip() {
		boolean isExist;
		for (ProductItem shipItem : this.currentShip.getGoods()) {
			isExist = false;
			for (ProductItem stockItem : this.harbor.getStock()) {
				if (shipItem.getName().equals(stockItem.getName())) {
					isExist = true;
					stockItem.incCount(shipItem.getCount());
				}
			}
			if (isExist == false)
				this.harbor.setGoodsItem(shipItem);
			this.currentShip.getGoods().remove(shipItem);
			
			this.ui_panel.updateTable();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/**
	 * Вывод информации о порте в строку.
	 */
	public String toString() {
		return this.status + " " + this.currentShip.toString();
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			setShip();
			if (this.currentShip == null)
				continue;

			if (isInterrupted())
				break;

			unloadShip();

			if (isInterrupted())
				return;

			currentShip = null;
		}
	}

}
