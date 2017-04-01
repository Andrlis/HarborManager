package DataPackage;

public class Pier {
	private boolean available;
	private String status;
	private Ship currentShip;

	public Pier() {
		this.available = true;
		this.status = "Free";
		this.currentShip = null;
	}

	public boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Ship getShip() {
		return this.currentShip;
	}

	public void setShip(Ship currentShip) {
		this.currentShip = currentShip;
	}
	
	public String toString(){
		return this.status+" "+this.currentShip.toString();
	}

}
