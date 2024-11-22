package com.poscodx.paint.point;

import com.poscodx.paint.i.Drawable;

public class Point implements Drawable {
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

	public void show() {
		System.out.println("Point [x="+x+", y="+y+"]을 그렸습니다.");
	}

	public void show(boolean visible) {
		if(visible) {
			show();
		} else {
			System.out.println("Point [x="+x+", y="+y+"]을 지웠습니다.");
		}
	}

	@Override
	public void draw() {
		show();
	}
}
