package Exercise_09._03Extra;

import java.util.Scanner;

import javafx.geometry.Point2D;

public class Exercise_09_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
		Point2D p1 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p2 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p3 = new Point2D(input.nextDouble(), input.nextDouble());

		double area = getTriangleArea(p1, p2, p3);
		if (area == 0) {
			System.out.println("The three points are on the same line");
		}
		else {
			System.out.println("The area of the triangle is " + area);
		}
	}

	public static double getTriangleArea(Point2D p1, Point2D p2, Point2D p3) {
		return Math.abs((p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
			- (p3.getX() - p1.getX()) * (p2.getY() - p1.getY())) / 2;
	}
}
