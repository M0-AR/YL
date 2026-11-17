package Exercise_05._07Extra;

import java.util.Scanner;

public class Exercise_05_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a positive integer: ");
		int number = input.nextInt();

		int coefficient = 1;
		int radicand = number;
		int factor = 2;
		while (factor * factor <= radicand) {
			if (radicand % (factor * factor) == 0) {
				coefficient *= factor;
				radicand /= factor * factor;
			}
			else {
				factor++;
			}
		}

		String result;
		if (radicand == 1) {
			result = String.valueOf(coefficient);
		}
		else if (coefficient == 1) {
			result = "sqrt(" + radicand + ")";
		}
		else {
			result = coefficient + "*sqrt(" + radicand + ")";
		}

		System.out.println("sqrt(" + number + ") is " + result);
	}
}
