package DataPackage;

public class ProductItem {

	private String name;
	private int count;
	
	public ProductItem(String name, int count){
		this.name = name;
		this.count = count;
	}
	
	public ProductItem(){
		this.name = "No name";
		this.count = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		 this.count = count;
	}
	
	public void incCount(int inc){
		this.count+=inc;
	}
	
	public void decCount(int dec){
		this.count-=dec;
	}
	
	public String toString(){
		return this.name+"; "+this.count+" items.";
	}
}
