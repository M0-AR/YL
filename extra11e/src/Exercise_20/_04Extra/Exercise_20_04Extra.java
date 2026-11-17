package Exercise_20._04Extra;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise_20_04Extra {
	record Point(double x, double y) {
		double distance() {
			return Math.hypot(x, y);
		}
	}

	public static void main(String[] args) {
		Point[] points = {
			new Point(3, 4), new Point(0, 5), new Point(3, 0),
			new Point(1, 1), new Point(-3, 4)
		};
		Arrays.sort(points, Comparator.comparingDouble(Point::distance)
				.thenComparingDouble(Point::x)
				.thenComparingDouble(Point::y));
		System.out.println(Arrays.toString(points));
		if (!points[0].equals(new Point(1, 1))) {
			throw new IllegalStateException("Unexpected first point: " + points[0]);
		}
		if (!points[4].equals(new Point(3, 4))) {
			throw new IllegalStateException("Unexpected last point: " + points[4]);
		}
		System.out.println("Exercise_20_04Extra OK");
	}
}
