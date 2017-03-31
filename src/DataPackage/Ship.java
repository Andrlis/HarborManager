package DataPackage;

import java.util.ArrayList;

public class Ship {

	private String name;
	private String country;
	private ArrayList<ProductItem> goods = new ArrayList<ProductItem>();

	public Ship(String name, String country, ArrayList<ProductItem> goods) {
		this.name = name;
		this.country = country;
		this.goods.addAll(goods);
	}

	public Ship() {
		this.name = "No name";
		this.country = "No country";
		this.goods.clear();
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

	public void setGoods(ArrayList<ProductItem> goods) {
		this.goods.addAll(goods);
	}

	public void setGoodsItem(ProductItem item) {
		this.goods.add(item);
	}

	public ArrayList<ProductItem> getGoods() {
		return this.goods;
	}

	public String toString() {
		return this.name + "; " + this.country + "; " + this.goods.toString();
	}
}