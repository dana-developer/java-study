package prob05;

public class Rectangle extends Shape implements Resizable {
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimeter() {
		return (width+height) * 2;
	}

	@Override
	public void resize(double rate) {
		height = rate * height;
		width = rate * width;
	}

}
