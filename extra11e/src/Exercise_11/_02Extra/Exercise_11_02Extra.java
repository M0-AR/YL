package Exercise_11._02Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise_11_02Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of objects: ");
		int[] weights = new int[input.nextInt()];
		System.out.print("Enter the weights of the objects: ");
		for (int i = 0; i < weights.length; i++) {
			weights[i] = input.nextInt();
		}

		Arrays.sort(weights);

		ArrayList<Bin> bins = new ArrayList<>();
		for (int i = weights.length - 1; i >= 0; i--) {
			boolean added = false;
			for (Bin bin : bins) {
				if (bin.addItem(weights[i])) {
					added = true;
					break;
				}
			}

			if (!added) {
				Bin bin = new Bin();
				bin.addItem(weights[i]);
				bins.add(bin);
			}
		}

		for (int i = 0; i < bins.size(); i++) {
			System.out.println("Container " + (i + 1)
				+ " contains objects with weight " + bins.get(i));
		}
	}

	static class Bin {
		private static final int MAX_WEIGHT = 10;
		private final ArrayList<Integer> weights = new ArrayList<>();
		private int totalWeight;

		public boolean addItem(int weight) {
			if (totalWeight + weight > MAX_WEIGHT) {
				return false;
			}
			weights.add(weight);
			totalWeight += weight;
			return true;
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			for (int weight : weights) {
				result.append(weight).append(' ');
			}
			return result.toString();
		}
	}
}
