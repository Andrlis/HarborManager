package DataPackage;

import java.util.ArrayList;
import java.util.Iterator;

import logic.HarborLogic;
import ui_package.PierPanel;

public class Pier extends Thread {

	PierPanel ui_panel;
	private String status;
	private Ship currentShip;
	private Harbor harbor;
	
	private ArrayList<ProductItem> itemsForLoading = new ArrayList<ProductItem>();

	public volatile boolean play = true;

	public Pier(Harbor harbor) {
		this.status = "Free";
		this.currentShip = new Ship();
		this.harbor = harbor;
		this.ui_panel = new PierPanel(this, harbor);
	}

	/**
	 * Возвращает PierPanel
	 */
	public PierPanel getPanel() {
		return this.ui_panel;
	}

	/**
	 * Формирование списка товаров для погрузки
	 */
	public ArrayList<ProductItem> getListOfLoadedItems(){
		return itemsForLoading;
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
	 * Получает корабль из очереди.
	 */
	private void setShip() {
		this.currentShip = this.harbor.getShipFromQueue();
	}

	/**
	 * Разгрузка корабля.
	 */
	public void unloadShip() {
		ProductItem curItem;

		while (currentShip.getGoods().isEmpty() == false) {

			curItem = currentShip.getGoods().get(0);
			this.harbor.unloadShip(curItem);
			this.currentShip.getGoods().remove(0);

			this.ui_panel.updateTable();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/**
	 * Вывод информации о порте в строку.
	 */
	public String toString() {
		if (this.currentShip != null)
			return this.status + " " + this.currentShip.toString();
		else
			return this.status + " No ship.";
	}

	/**
	 * Продолжить работу потока.
	 */
	public synchronized void play() {
		play = true;
		notify();
	}

	/**
	 * Приостановить поток.
	 */
	public void pause() {
		play = false;
	}

	/**
	 * Проверка, нужна ли остановка.
	 */
	private synchronized void checkPause() {
		while (!play) {
			try {
				wait();
			} catch (InterruptedException ex) {
				interrupt();
				play = true;
			}
		}
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			setShip();
			this.ui_panel.updatePanel();

			if (this.currentShip == null) {
				ui_panel.resetPanel();
				continue;
			}

			if (isInterrupted())
				return;

			if (this.currentShip != null) {
				unloadShip();
				this.ui_panel.updatePanel();
			}

			checkPause();
			if (isInterrupted())
				return;
			harbor.loadShip(currentShip, itemsForLoading);

			if (isInterrupted())
				return;

			try {
				sleep(2000);
			} catch (InterruptedException e) {}

			if (isInterrupted())
				return;
			
			if(currentShip!=null)
				this.harbor.addShipToQueue(currentShip);
			
			currentShip = null;

		}
	}

}
