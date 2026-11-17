package Exercise_06._11Extra;

import java.util.Scanner;

public class Exercise_06_11Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a numerator: ");
		int numerator = input.nextInt();
		System.out.print("Enter a denominator: ");
		int denominator = input.nextInt();

		int gcd = gcd(numerator, denominator);
		int reducedNumerator = numerator / gcd;
		int reducedDenominator = denominator / gcd;

		if (reducedNumerator < reducedDenominator) {
			System.out.println(reducedNumerator + " / " + reducedDenominator
				+ " is a proper fraction");
		}
		else if (reducedDenominator == 1) {
			System.out.println(numerator + " / " + denominator
				+ " is an improper fraction and it can be reduced to "
				+ reducedNumerator);
		}
		else {
			System.out.println(numerator + " / " + denominator
				+ " is an improper fraction and its mixed fraction is "
				+ reducedNumerator / reducedDenominator + " + "
				+ reducedNumerator % reducedDenominator + " / "
				+ reducedDenominator);
		}
	}

	public static int gcd(int number1, int number2) {
		while (number2 != 0) {
			int remainder = number1 % number2;
			number1 = number2;
			number2 = remainder;
		}
		return number1;
	}
}
