package Exercise_18._03Extra;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise_18_03Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of items: ");
		int numberOfItems = input.nextInt();

		System.out.print("Enter the weights for each item: ");
		double[] weights = new double[numberOfItems];
		for (int i = 0; i < numberOfItems; i++) {
			weights[i] = input.nextDouble();
		}

		System.out.print("Enter the weight limit for the bag: ");
		double weightLimit = input.nextDouble();

		ArrayList<Integer> bag = m(numberOfItems - 1, weightLimit, weights);
		System.out.println("The maximum weight of the items placed in the bag is "
			+ bagWeight(bag, weights));

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

	public static ArrayList<Integer> m(int i, double weightLimit,
			double[] w) {
		if (i < 0 || weightLimit <= 0) {
			return new ArrayList<>();
		}
		if (w[i] > weightLimit) {
			return m(i - 1, weightLimit, w);
		}

		ArrayList<Integer> without = m(i - 1, weightLimit, w);
		ArrayList<Integer> with = m(i - 1, weightLimit - w[i], w);
		with.add(i);

		if (bagWeight(without, w) > bagWeight(with, w)) {
			return without;
		}
		return with;
	}

	public static double bagWeight(ArrayList<Integer> bag, double[] w) {
		double total = 0;
		for (int index : bag) {
			total += w[index];
		}
		return total;
	}
}
