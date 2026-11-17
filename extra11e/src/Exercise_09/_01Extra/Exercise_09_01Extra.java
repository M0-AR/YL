package Exercise_09._01Extra;

import java.util.Scanner;

import javafx.geometry.Point2D;

public class Exercise_09_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
		Point2D p1 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p2 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p3 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p4 = new Point2D(input.nextDouble(), input.nextDouble());

		Point2D intersection = getIntersectingPoint(p1, p2, p3, p4);
		if (intersection == null) {
			System.out.println("The two lines are parallel");
		}
		else {
			System.out.println("The intersecting point is at ("
				+ intersection.getX() + ", " + intersection.getY() + ")");
		}
	}

	public static Point2D getIntersectingPoint(
			Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		double a = p1.getY() - p2.getY();
		double b = -(p1.getX() - p2.getX());
		double c = p3.getY() - p4.getY();
		double d = -(p3.getX() - p4.getX());
		double e = a * p1.getX() - (p1.getX() - p2.getX()) * p1.getY();
		double f = c * p3.getX() - (p3.getX() - p4.getX()) * p3.getY();

		double determinant = a * d - b * c;
		if (determinant == 0) {
			return null;
		}

		double x = (e * d - b * f) / determinant;
		double y = (a * f - e * c) / determinant;
		return new Point2D(x, y);
	}
}
