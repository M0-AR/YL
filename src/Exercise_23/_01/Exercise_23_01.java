// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._01;

import java.util.Comparator;

public class Exercise_23_01 {

	public static <E extends Comparable<E>> void bubbleSort(E[] list) {
		boolean needNextPass = true;

		for (int k = 1; k < list.length && needNextPass; k++) {

			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (list[i].compareTo(list[i + 1]) > 0) {

					E temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;

					needNextPass = true;
				}
			}
		}
	}


	public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
		boolean needNextPass = true;

		for (int k = 1; k < list.length && needNextPass; k++) {

			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (comparator.compare(list[i], list[i + 1]) > 0) {

					E temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;

					needNextPass = true;
				}
			}
		}
	}


	public static void main(String[] args) {

		Integer[] listArray = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};


		Double[] doubleArray = {3.4, 1.3, -22.1, 14.8, 6.0, 2.3, 12.2};


		Character[] charArray = {'a', 'J', 'r'};


		String[] stringArray = {"Tom", "Susan", "Kim"};


		bubbleSort(listArray);
		bubbleSort(doubleArray);
		bubbleSort(charArray);
		bubbleSort(stringArray);

		printList(listArray);
		printList(charArray);
		printList(stringArray);
		printList(doubleArray);


		GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
			new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
			new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
			new Circle(6.5), new Rectangle(4, 5)};


		bubbleSort(list, new GeometricObjectComparator());


		printList(list);
	}


	public static void printList(Object[] list) {
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
		System.out.println();
	}


	public static void printList(GeometricObject[] list) {
		System.out.print("Sorted elements: ");
		for (GeometricObject e: list) {
			System.out.printf("%.2f ", e.getArea());
		}
		System.out.println();
	}
}
