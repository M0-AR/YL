// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._09;

import java.util.Comparator;

public class Exercise_23_09 {
	public static void main(String[] args) {

		GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
			new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
			new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
			new Circle(6.5), new Rectangle(4, 5)};

		Heap<GeometricObject> heap = new Heap<>(new GeometricObjectComparator());

		for (int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}

		for (int i = list.length - 1; i > 0; i--) {
			list[i] = heap.remove();
		}

		for (GeometricObject e: list)
			System.out.printf("%.2f ", e.getArea());
		System.out.println();
	}
}
