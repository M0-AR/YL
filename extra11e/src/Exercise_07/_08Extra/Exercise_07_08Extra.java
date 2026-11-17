package Exercise_07._08Extra;

import java.util.Scanner;

public class Exercise_07_08Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the order of the polynomial n: ");
		int order = input.nextInt();

		System.out.print("Enter coefficients: ");
		double[] coefficients = new double[order + 1];
		for (int i = order; i >= 0; i--) {
			coefficients[i] = input.nextDouble();
		}

		System.out.print("Enter x: ");
		double x = input.nextDouble();

		System.out.println("The polynomial expression is "
			+ evaluatePolynomial(coefficients, x));
	}

	public static double evaluatePolynomial(double[] a, double x) {
		double result = 0;
		for (int i = a.length - 1; i >= 0; i--) {
			result = result * x + a[i];
		}
		return result;
	}
}
