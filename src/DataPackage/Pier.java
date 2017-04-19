package DataPackage;

import java.util.Iterator;

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
	 * ���������� PierPanel
	 */
	public PierPanel getPanel() {
		return this.ui_panel;
	}

	/**
	 * ��������� ��������� �����.
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
	 * �������� ������� �� �������.
	 */
	private void setShip() {
		this.currentShip = this.harbor.getShipFromQueue();
	}

	/**
	 * ��������� �������.
	 */
	public void unloadShip() {
		ProductItem curItem;
		
		while(currentShip.getGoods().isEmpty()==false) {

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
		//this.currentShip.getGoods().clear();
		this.ui_panel.updateTable();
	}

	/**
	 * ����� ���������� � ����� � ������.
	 */
	public String toString() {
		if (this.currentShip != null)
			return this.status + " " + this.currentShip.toString();
		else
			return this.status + " No ship.";
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

			if (isInterrupted())
				return;

			// try{
			// sleep(2000);
			// }catch(InterruptedException e){}

			currentShip = null;

		}
	}

}
