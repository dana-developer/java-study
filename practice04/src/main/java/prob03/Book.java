package prob03;

public class Book {
	private int no;
	private String title;
	private String author;
	private int stateCode;
	
	public Book(int no, String title, String author) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}
	
	public void rent() {
		if(stateCode == 1) {
			stateCode = 0;
			System.out.println(title+"이(가) 대여 됐습니다.");
		}
	}
	
	public void print() {
		String isRent = "";
		
		if(stateCode == 0) {
			isRent = "대여중";
		} else if(stateCode == 1) {
			isRent = "재고있음";
		}
		
		System.out.println(
				"책 제목:"+title+
				", 작가:"+author+
				",대여 유무:"+isRent
				);
	}
}
