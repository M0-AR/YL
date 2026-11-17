package Exercise_18._02Extra;

import java.util.Scanner;

public class Exercise_18_02Extra {
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

		System.out.println("The maximum weight of the items placed in the bag is "
			+ m(numberOfItems - 1, weightLimit, weights));
	}

	public static double m(int i, double weightLimit, double[] w) {
		if (i < 0 || weightLimit <= 0) {
			return 0;
		}
		if (w[i] > weightLimit) {
			return m(i - 1, weightLimit, w);
		}
		return Math.max(m(i - 1, weightLimit, w),
			w[i] + m(i - 1, weightLimit - w[i], w));
	}
}
