// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._03;

public class Rectangle
		extends GeometricObject {
	private double width;
	private double height;

	public Rectangle() {
	}

	public Rectangle(
		double width, double height) {
		this.width = width;
		this.height = height;
	}

	public Rectangle(
		double width, double height, String color, boolean filled) {
		this.width = width;
		this.height = height;
		setColor(color);
		setFilled(filled);
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this. width = width;
	}


	public double getheight() {
		return height;
	}


	public void setheight(double height) {
		this.height = height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimeter() {
		return 2 * (width * height);
	}

	@Override
	public String toString() {
		return super.toString() + " Rectangle, Created: "
			+ getDateCreated() + ", Width: " + width +
			", Height: " + height;
	}
}
