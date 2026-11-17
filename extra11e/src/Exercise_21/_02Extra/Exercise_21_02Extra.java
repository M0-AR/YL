package Exercise_21._02Extra;

import java.util.Set;
import java.util.TreeSet;

public class Exercise_21_02Extra {
	public static void main(String[] args) {
		double[][][] scores = {
			{{7.5, 20.5}, {9.0, 22.5}, {15, 33.5}, {13, 21.5}, {15, 2.5}},
			{{4.5, 21.5}, {9.0, 22.5}, {15, 34.5}, {12, 20.5}, {14, 9.5}},
			{{6.5, 30.5}, {9.4, 10.5}, {11, 33.5}, {11, 23.5}, {10, 2.5}},
			{{6.5, 23.5}, {9.4, 32.5}, {13, 34.5}, {11, 20.5}, {16, 7.5}},
			{{8.5, 26.5}, {9.4, 52.5}, {13, 36.5}, {13, 24.5}, {16, 2.5}},
			{{9.5, 20.5}, {9.4, 42.5}, {13, 31.5}, {12, 20.5}, {16, 6.5}}};

		Set<Double> distinctScores = new TreeSet<>();
		for (double[][] student : scores) {
			for (double[] exam : student) {
				for (double score : exam) {
					distinctScores.add(score);
				}
			}
		}

		int count = 0;
		for (double score : distinctScores) {
			if (count > 0) {
				System.out.print(count % 5 == 0 ? "\n" : " ");
			}
			System.out.print(score);
			count++;
		}
		System.out.println();
	}
}
