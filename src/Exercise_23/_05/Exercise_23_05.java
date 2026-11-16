// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._05;

import java.util.Comparator;

public class Exercise_23_05 {

	public static <E extends Comparable<E>> void heapSort(E[] list) {

		Heap<E> heap = new Heap<>();


		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);


		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}

	public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {

		HeapA<E> heap = new HeapA<>(comparator);


		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);


		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}


	public static void main(String[] args) {

		Integer[] intArray = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};


		Double[] doubleArray = {3.4, 1.3, -22.1, 14.8, 6.0, 2.3, 12.2};


		Character[] charArray = {'a', 'J', 'r'};


		String[] stringArray = {"Tom", "Susan", "Kim"};


		heapSort(intArray);
		heapSort(doubleArray);
		heapSort(charArray);
		heapSort(stringArray);


		System.out.print("Sorted Integers: ");
		printList(intArray);

		System.out.print("Sorted Doubles: ");
		printList(doubleArray);

		System.out.print("Sorted Characters: ");
		printList(charArray);

		System.out.print("Sorted Strings: ");
		printList(stringArray);


		GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
			new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
			new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
			new Circle(6.5), new Rectangle(4, 5)};
		heapSort(list, new GeometricObjectComparator());
		System.out.print("Sorted elements: ");
		for (GeometricObject e: list) {
			System.out.printf("%.2f ", e.getArea());
		}
		System.out.println();
	}


	public static void printList(Object[] list) {
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
		System.out.println();
	}
}
