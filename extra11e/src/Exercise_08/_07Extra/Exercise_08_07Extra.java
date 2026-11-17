package Exercise_08._07Extra;

public class Exercise_08_07Extra {
	public static void main(String[] args) {
		double[][][] scores = {
			{{7.5, 20.5}, {9.0, 22.5}, {15, 33.5}, {13, 21.5}, {15, 2.5}},
			{{4.5, 21.5}, {9.0, 22.5}, {15, 34.5}, {12, 20.5}, {14, 9.5}},
			{{6.5, 30.5}, {9.4, 10.5}, {11, 33.5}, {11, 23.5}, {10, 2.5}},
			{{6.5, 23.5}, {9.4, 32.5}, {13, 34.5}, {11, 20.5}, {16, 7.5}},
			{{8.5, 26.5}, {9.4, 52.5}, {13, 36.5}, {13, 24.5}, {16, 2.5}},
			{{9.5, 20.5}, {9.4, 42.5}, {13, 31.5}, {12, 20.5}, {16, 6.5}}
		};

		for (int exam = 0; exam < 5; exam++) {
			double sum = 0;
			for (int student = 0; student < scores.length; student++) {
				sum += scores[student][exam][0] + scores[student][exam][1];
			}
			System.out.println("Average for Exam " + (exam + 1) + " is "
				+ sum / scores.length);
		}
	}
}
