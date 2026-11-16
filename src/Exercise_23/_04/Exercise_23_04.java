// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_23._04;

public class Exercise_23_04 {
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	public static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}


	public static int partition(int[] list, int first, int last) {
		int middle = list[(list.length - 1) / 2];

		int pivot = median(first, middle, last);
		int low = first + 1;
		int high = last;

		while (high > low) {

			while (low <= high && list[low] <= pivot)
				low++;


			while (low <= high && list[high] > pivot)
				high--;


			if (high > low) {
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;


		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		}
		else {
			return first;
		}
	}


	public static void main(String[] args) {
		int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
		quickSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
		System.out.println();
	}


	public static int median(int first, int middle, int last) {
		return Math.max(Math.min(first, middle),
			Math.min(Math.max(first, middle), last));
	}
}
