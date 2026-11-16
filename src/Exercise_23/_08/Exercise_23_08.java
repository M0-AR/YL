// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._08;

public class Exercise_23_08 {
	public static <E extends Comparable<E>> void sort(E[] list) {

		Heap<E> heap = new Heap<>(list);


		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}

	public static void main(String[] args) {
		Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
		sort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
		System.out.println();
	}
}
