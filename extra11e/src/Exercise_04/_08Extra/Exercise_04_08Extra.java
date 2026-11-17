package Exercise_04._08Extra;

import java.util.Scanner;

public class Exercise_04_08Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a floating point number: ");
		double value = input.nextDouble();

		String text = String.valueOf(value);
		int decimalPoint = text.indexOf('.');
		if (decimalPoint < 0) {
			System.out.println("The number of digits before the decimal point in "
				+ value + " is " + text.length());
		}
		else {
			System.out.println("The number of digits before the decimal point in "
				+ value + " is " + decimalPoint);
		}
	}
}
