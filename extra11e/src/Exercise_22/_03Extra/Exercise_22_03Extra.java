package Exercise_22._03Extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise_22_03Extra {
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

		ArrayList<Integer> bag = m(numberOfItems, weightLimit, weights);
		int total = 0;
		for (int index : bag) {
			total += weights[index];
		}

		System.out.println("The maximum weight of the items placed in the bag is "
			+ total);

		System.out.print("The items in the bag are");
		for (int index : bag) {
			System.out.print(" #" + (index + 1));
		}
		System.out.println();

		System.out.print("The weights of the items in the bag are");
		for (int index : bag) {
			System.out.print(" " + weights[index]);
		}
		System.out.println();
	}

	public static ArrayList<Integer> m(int i, int weightLimit, int[] w) {
		ArrayList<Integer> bag = new ArrayList<>();
		if (i <= 0 || weightLimit <= 0) {
			return bag;
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

		int capacity = weightLimit;
		for (int item = i; item > 0; item--) {
			if (table[item][capacity] != table[item - 1][capacity]) {
				bag.add(item - 1);
				capacity -= w[item - 1];
			}
		}
		Collections.reverse(bag);
		return bag;
	}
}
