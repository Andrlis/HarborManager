package DataPackage;

public class ProductItem {

	private int id;
	private String name;
	private int count;
	private double weight;

	public ProductItem(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public ProductItem() {
		this.name = "No name";
		this.count = 0;
		this.weight = 0;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void incCount(int inc) {
		this.count += inc;
	}

	public void decCount(int dec) {
		this.count -= dec;
	}

	public String toString() {
		return this.name + "; " + this.count + " items.";
	}
}
