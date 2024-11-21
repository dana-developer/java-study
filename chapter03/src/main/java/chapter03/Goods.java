package chapter03;

public class Goods {
	public static int countOfGoods = 0; //공유변수(클래스 변수)
	
	private String name; //default (같은 패키지 내에서는 접근 가능)
	private int price;
	private int countStock;
	private int countSold;
	
	public Goods() {
		// 클래스 이름은 생략 가능
		countOfGoods++;
	}
	
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
		// 상태 보호
		if(price < 0) {
			price = 0;
		}
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
	
	public int calcDiscountPrice(float discountRate) {
		// int * float = float이므로 명시적 casting해야 함.(짤릴 수 있으므로) float -> int
		// int -> float는 암시적 casting(float가 더 크므로)
		int result = price - (int) (price * discountRate); // 지역변수는 초기화를 해야 함
		return result;
	}
}
