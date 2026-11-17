package Exercise_18._06Extra;

import java.util.Scanner;

public class Exercise_18_06Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] list = new int[6];

		System.out.print("Enter 6 integers: ");
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextInt();
		}

		cube(list);

		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				System.out.print(" ");
			}
			System.out.print(list[i]);
		}
		System.out.println();
	}

	public static void cube(int[] list) {
		cube(list, 0, list.length - 1);
	}

	public static void cube(int[] list, int low, int high) {
		if (low <= high) {
			list[low] = list[low] * list[low] * list[low];
			cube(list, low + 1, high);
		}
	}
}
