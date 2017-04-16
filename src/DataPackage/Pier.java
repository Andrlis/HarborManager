package DataPackage;

import java.util.ArrayList;

public class Pier extends Thread {

	private String status;
	private Ship currentShip;
	private Harbor harbor;

	public Pier(Harbor harbor) {
		this.status = "Free";
		this.currentShip = new Ship();
		this.harbor = harbor;
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
					// delete from ship!!!
				}
			}
			if (isExist == false)
				this.harbor.setGoodsItem(shipItem);
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
