package chapter03;

public class Goods {
	private String name; //default (같은 패키지 내에서는 접근 가능)
	private int price;
	private int countStock;
	private int countSold;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	public void printInfo() {
		//this.name (이때, this 생략 가능)
		System.out.println(
			"상품이름 : "+ name +
			", 가격 : " + price +
			", 판매량 : "+ countSold +
			", 재고량 : " + countStock);
	}
}
