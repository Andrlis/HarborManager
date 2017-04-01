/*
 * Класс "Склад"
 */
package DataPackage;

import java.util.ArrayList;

public class Stock {

	private ArrayList<ProductItem> goods = new ArrayList<ProductItem>();
	private int itemCount;

	public Stock() {
		this.itemCount = 0;
		this.goods.clear();
	}

	public Stock(ArrayList<ProductItem> goods) {
		this.goods.addAll(goods);
		for (int i = 0; i <= goods.size(); i++) {
			this.itemCount += goods.get(i).getCount();
		}
	}

	public ArrayList<ProductItem> getGoods() {
		return this.goods;
	}

	public void setGoods(ArrayList<ProductItem> goods) {
		this.goods.addAll(goods);
	}

	public void setGoodsItem(ProductItem item) {
		this.goods.add(item);
	}

	public int getCount() {
		return this.itemCount;
	}

	public void setCount(int count) {
		this.itemCount = count;
	}

	public void incCount(int inc) {
		this.itemCount += inc;
	}

	public void decCount(int dec) {
		this.itemCount -= dec;
	}
}
