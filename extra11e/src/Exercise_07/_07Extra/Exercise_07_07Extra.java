package Exercise_07._07Extra;

import java.util.Scanner;

public class Exercise_07_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter list1: ");
		int[] list1 = readList(input);
		System.out.print("Enter list2: ");
		int[] list2 = readList(input);

		int[] common = commonElements(list1, list2);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < common.length; i++) {
			if (i > 0) {
				result.append(' ');
			}
			result.append(common[i]);
		}

		System.out.println("The common elements are " + result);
	}

	private static int[] readList(Scanner input) {
		int size = input.nextInt();
		int[] list = new int[size];
		for (int i = 0; i < size; i++) {
			list[i] = input.nextInt();
		}
		return list;
	}

	public static int[] commonElements(int[] list1, int[] list2) {
		int[] result = new int[list2.length];
		int size = 0;
		for (int i = 0; i < list2.length; i++) {
			if (contains(list1, list2[i])) {
				result[size++] = list2[i];
			}
		}

		int[] common = new int[size];
		System.arraycopy(result, 0, common, 0, size);
		return common;
	}

	private static boolean contains(int[] list, int value) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == value) {
				return true;
			}
		}
		return false;
	}
}
