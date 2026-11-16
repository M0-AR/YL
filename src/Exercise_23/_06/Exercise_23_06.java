// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._06;

import java.util.Comparator;

public class Exercise_23_06 {

	public static boolean ordered(int[] list) {
		return ordered(list, true);
	}



	public static boolean ordered(int[] list, boolean ascending) {
		boolean descending = !ascending;

		for (int i = 0; i < list.length - 1; i++) {
			if (ascending && list[i] > list[i + 1]) {
				return false;
			}
			else if (descending && list[i] < list[i + 1]) {
				return false;
			}
		}
		return true;
	}



	public static boolean ordered(double[] list) {
		return ordered(list, true);
	}



	public static boolean ordered(double[] list, boolean ascending) {
		boolean descending = !ascending;

		for (int i = 0; i < list.length - 1; i++) {
			if (ascending && list[i] > list[i + 1])
				return false;
			else if (descending && list[i] < list[i + 1])
				return false;
		}
		return true;
	}



	public static <E extends Comparable<E>> boolean ordered(E[] list) {
		return ordered(list, true);
	}



	public static <E extends Comparable<E>> boolean
			ordered(E[] list, boolean ascending) {
		boolean descending = !ascending;

		for (int i = 0; i < list.length - 1; i++) {
			if (ascending && list[i].compareTo(list[i + 1]) > 0)
				return false;
			else if (descending && list[i].compareTo(list[i + 1]) < 0)
				return false;
		}
		return true;
	}



	public static <E> boolean ordered(
			E[] list, Comparator<? super E> comparator) {
		return ordered(list, comparator, true);
	}



	public static <E> boolean ordered(E[] list,
			Comparator<? super E> comparator, boolean ascending) {
		boolean descending = !ascending;

		for (int i = 0; i < list.length - 1;  i++) {
			if (ascending && comparator.compare(list[i], list[i + 1]) > 0)
				return false;
			else if (descending && comparator.compare(list[i], list[i +1]) < 0)
				return false;
		}
		return true;
	}


	public static void main(String[] args) {

		int[] list1 = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
		int[] list2 = {1, 3, 5, 6, 12, 14, 20};
		int[] list3 = {20, 14, 12, 5, 3, -2};


		double[] list4 = {2.1, 3.5, 2.1, 5.3, 6.7, 1.0, -2.6, 3.8, 14.2, 12.1};
		double[] list5 = {1.1, 3.0, 5.7, 6.8, 12.0, 14.2, 20.5};
		double[] list6 = {20.0, 14.0, 12.0, 5.5, 3.0, -2.0};


		Integer[] intArray = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};


		Character[] charArray = {'J', 'a', 'r'};


		String[] stringArray = {"Tom", "Carl", "Ann"};


		GeometricObject[] list7 = {new Circle(5), new Rectangle(4,5),
			new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
			new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
			new Circle(6.5), new Rectangle(4, 5)};

		GeometricObject[] list8 = {new Circle(3), new Rectangle(3, 10),
			new Circle(5)};

		GeometricObject[] list9 = {new Circle(10), new Rectangle(3, 10),
			new Circle(2)};


		System.out.println("List1 is" +
			(ordered(list1) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("List2 is" +
			(ordered(list2) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("List3 is" +
			(ordered(list3, false) ? " " : " not ") + "ordered in descending order.");

		System.out.println("List2 is" +
			(ordered(list2, false) ? " " : " not ") + "ordered in descending order.");

		System.out.println("List4 is" +
			(ordered(list4) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("List5 is" +
			(ordered(list5) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("List6 is" +
			(ordered(list6, false) ? " " : " not ") + "ordered in descending order.");

		System.out.println("List5 is" +
			(ordered(list5, false) ? " " : " not ") + "ordered in descending order.");

		System.out.println("The array of Integers is" +
			(ordered(intArray) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("The array of Characters is" +
			(ordered(charArray) ? " " : " not ") + "ordered in ascending order.");

		System.out.println("The array of Strings is" +
			(ordered(stringArray, false) ? " " : " not ") +
			"ordered in descending order.");

		System.out.println("The array of Characters is" +
			(ordered(charArray, false) ? " " : " not ") +
			"ordered in descending order.");

		System.out.println("List7 is" + (ordered(list7,
			new GeometricObjectComparator()) ? " " : " not ") +
			"ordered in ascending order.");

		System.out.println("List8 is" + (ordered(list8,
			new GeometricObjectComparator()) ? " " : " not ") +
			"ordered in ascending order.");

		System.out.println("List9 is" + (ordered(list9,
			new GeometricObjectComparator(), false) ? " " : " not ") +
			"ordered in descending order.");

		System.out.println("List8 is" + (ordered(list8,
			new GeometricObjectComparator(), false) ? " " : " not ") +
			"ordered in descending order.");
	}
}
