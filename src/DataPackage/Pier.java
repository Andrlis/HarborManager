package DataPackage;

public class Pier extends Thread {

	private String status;
	private Ship currentShip;
	private Harbor harbor;

	public Pier(Harbor harbor) {
		this.status = "Free";
		this.currentShip = new Ship();
		this.harbor = harbor;
	}

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
	synchronized public void setShip() {
		if (this.harbor.getShipQueue().isEmpty() == true) {
			this.currentShip = null;
		}
		this.currentShip = this.harbor.getShipQueue().get(0);
		harbor.getShipQueue().remove(0);
	}

	public String toString() {
		return this.status + " " + this.currentShip.toString();
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			setShip();
			if(this.currentShip == null) continue;
			
			if(isInterrupted()) break;
			try{
				sleep(1000);
			} catch (InterruptedException e){
				return;
			}
			currentShip = null;
		}
	}

}
