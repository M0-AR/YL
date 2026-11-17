package Exercise_22._02Extra;

import java.util.Scanner;

public class Exercise_22_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of items: ");
		int numberOfItems = input.nextInt();

		System.out.print("Enter the weights for each item: ");
		int[] weights = new int[numberOfItems];
		for (int i = 0; i < numberOfItems; i++) {
			weights[i] = input.nextInt();
		}

		System.out.print("Enter the weight limit for the bag: ");
		int weightLimit = input.nextInt();

		System.out.println("The maximum weight of the items placed in the bag is "
			+ m(numberOfItems, weightLimit, weights));
	}

	public static int m(int i, int weightLimit, int[] w) {
		if (i <= 0 || weightLimit <= 0) {
			return 0;
		}

		int[][] table = new int[i + 1][weightLimit + 1];
		for (int item = 1; item <= i; item++) {
			for (int capacity = 0; capacity <= weightLimit; capacity++) {
				table[item][capacity] = table[item - 1][capacity];
				if (w[item - 1] <= capacity) {
					table[item][capacity] = Math.max(
						table[item][capacity],
						w[item - 1] + table[item - 1][capacity - w[item - 1]]);
				}
			}
		}
		return table[i][weightLimit];
	}
}
