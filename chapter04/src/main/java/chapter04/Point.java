package chapter04;

public class Point {
	private int x;
	private int y;
	
	// 기본 생성자는 생성자 1개라도 있으면 따로 선언해야 한다
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
}
