package Exercise_09._02Extra;

import java.util.Scanner;

import javafx.geometry.Point2D;

public class Exercise_09_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
		Point2D p1 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p2 = new Point2D(input.nextDouble(), input.nextDouble());
		Point2D p3 = new Point2D(input.nextDouble(), input.nextDouble());

		Point2D center = getCenterPoint(p1, p2, p3);
		if (center == null) {
			System.out.println("The three points are on the same line");
		}
		else {
			System.out.println("The center point is at ("
				+ center.getX() + ", " + center.getY() + ")");
		}
	}

	public static Point2D getCenterPoint(Point2D p1, Point2D p2, Point2D p3) {
		Point2D midpoint23 = p2.midpoint(p3);
		Point2D midpoint13 = p1.midpoint(p3);
		return Exercise_09._01Extra.Exercise_09_01Extra.getIntersectingPoint(
			p1, midpoint23, p2, midpoint13);
	}
}
