package paint;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
//		setX(x);
//		setY(y);
		super(x, y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override // 어노테이션
	public void show() { 
//		super.show(); // 부모 메소드를 명시적으로 접근
		System.out.println("Point [x="+getX()+", y="+getY()+", color="+color+"]을 그렸습니다.");
	}

	@Override
	public void draw() {
		show();
	}
}
