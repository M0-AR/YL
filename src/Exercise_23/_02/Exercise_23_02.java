// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._02;

import java.util.Comparator;
import java.util.Arrays;

public class Exercise_23_02 {

	public static <E extends Comparable<E>> void mergeSort(E[] list) {
		if (list.length > 1) {

			E[] firstHalf = (E[])new Comparable[list.length / 2];
			System.arraycopy(list, 0 , firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);


			int secondHalfLength = list.length - list.length / 2;
			E[] secondHalf = (E[])(new Comparable[secondHalfLength]);
			System.arraycopy(list, list.length / 2,
				secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);


			merge(firstHalf, secondHalf, list);
		}
	}


	public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1].compareTo(list2[current2]) < 0)
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}

	public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {

		if (list.length > 1) {

			E[] firstHalf = Arrays.copyOf(list, list.length / 2);
			mergeSort(firstHalf, comparator);


			E[] secondHalf = Arrays.copyOfRange(list, list.length / 2, list.length);
			mergeSort(secondHalf, comparator);


			merge(firstHalf, secondHalf, list, comparator);
		}
	}


	public static <E> void merge(E[] list1, E[] list2, E[] temp,
			Comparator<? super E> comparator) {
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;

		while (current1 < list1.length && current2 < list2.length) {
			if (comparator.compare(list1[current1], list2[current2]) < 0)
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}


	public static void main(String[] args) {
		Integer[] intArray = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};


		Double[] doubleArray = {3.4, 1.3, -22.1, 14.8, 6.0, 2.3, 12.2};


		Character[] charArray = {'a', 'J', 'r'};


		String[] stringArray = {"Tom", "Susan", "Kim"};


		mergeSort(intArray);
		mergeSort(doubleArray);
		mergeSort(charArray);
		mergeSort(stringArray);


		printList(intArray);
		printList(charArray);
		printList(stringArray);
		printList(doubleArray);


		GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
			new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
			new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
			new Circle(6.5), new Rectangle(4, 5)};


		mergeSort(list, new GeometricObjectComparator());


		printList(list);
	}


	public static void printList(Object[] list) {
		for (int i = 0; i < list.length; i ++)
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
