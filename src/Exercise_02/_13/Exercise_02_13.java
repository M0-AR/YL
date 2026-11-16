// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._13;

import java.util.Scanner;

public class Exercise_02_13 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final double MONTHLY_INTEREST_RATE = 0.00417;


		System.out.print("Enter the monthly saving amount: ");
		double savingAmount = input.nextDouble();


		double total = 100 * (1 + MONTHLY_INTEREST_RATE);

		total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);

		total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);

		total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);

		total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);

		total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);


		System.out.println("After the sixth month, the account value is " + total);
	}
}
