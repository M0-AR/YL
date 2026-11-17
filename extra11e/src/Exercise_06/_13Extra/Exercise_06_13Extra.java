package Exercise_06._13Extra;

import java.util.Scanner;

public class Exercise_06_13Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the current year tuition: ");
		double tuition = input.nextDouble();

		System.out.printf("%9s%20s%n", "Rate", "Number of Years");
		for (int percent = 3; percent <= 10; percent++) {
			double rate = percent / 100.0;
			System.out.printf("%8.1f%%%20d%n", rate * 100,
				getNumberOfYears(tuition, rate));
		}
	}

	public static int getNumberOfYears(double tuition, double rate) {
		int years = 0;
		double currentTuition = tuition;
		while (currentTuition < 2 * tuition) {
			currentTuition *= 1 + rate;
			years++;
		}
		return years;
	}
}
