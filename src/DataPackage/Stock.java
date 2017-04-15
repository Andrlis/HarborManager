/*
 * Класс "Склад"
 */
package DataPackage;

import java.util.ArrayList;

public class Stock {

	private ArrayList<ProductItem> goods = new ArrayList<ProductItem>();

	public Stock() {
		this.goods.clear();
	}

	public Stock(ArrayList<ProductItem> goods) {
		this.goods.addAll(goods);
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
		return this.goods.size();
	}

	
	/**
	 * Увеличение кол-ва товара на складе
	 * @param inc
	 * @param item
	 */
	public void incItemCount(int inc, ProductItem item){
		for(int i = 0; i<=goods.size();i++){
			if(goods.get(i).getName().equals(item.getName())){
				goods.get(i).incCount(inc);
			}
		}
	}
	
	/**
	 * Уменьшение кол-ва товара на складе
	 * @param dec
	 * @param item
	 */
	public void decItemCount(int dec, ProductItem item){
		for(int i = 0; i<=goods.size();i++){
			if(goods.get(i).getName().equals(item.getName())){
				goods.get(i).decCount(dec);
			}
		}
	}
}
