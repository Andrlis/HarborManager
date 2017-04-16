package DataPackage;

import java.util.ArrayList;

public class Ship {

	private int id;
	private String name;
	private String country;
	private double maxWeight;
	private ArrayList<ProductItem> goods = new ArrayList<ProductItem>();

	public Ship(String name, String country, ArrayList<ProductItem> goods, double weight) {
		this.name = name;
		this.country = country;
		this.goods.addAll(goods);
		this.maxWeight = weight;
	}

	public Ship() {
		this.name = "No name";
		this.country = "No country";
		this.goods.clear();
	}

	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getMaxWeight() {
		return this.maxWeight;
	}

	public void setMaxWeight(double weight) {
		this.maxWeight = weight;
	}

	public void setGoods(ArrayList<ProductItem> goods) {
		this.goods.addAll(goods);
	}

	public ArrayList<ProductItem> getGoods() {
		return this.goods;
	}

	public void setGoodsItem(ProductItem item) {
		this.goods.add(item);
	}

	public String toString() {
		return this.name + "; " + this.country + "; " + this.goods.toString();
	}
}
