// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._22;

import java.util.Scanner;

public class Exercise_02_22 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print(
			"Enter an amount as integer, for example 1156 for 11.56: ");
		int amount = input.nextInt();

		int remainingAmount = amount;


		int numberOfOneDollars = remainingAmount / 100;
		remainingAmount %= 100;


		int numberOfQuarters = remainingAmount / 25;
		remainingAmount %= 25;


		int numberOfDimes = remainingAmount / 10;
		remainingAmount %= 10;


		int numberOfNickels = remainingAmount / 5;
		remainingAmount %= 5;


		int numberOfPennies = remainingAmount;


		System.out.println("Your amount " + amount / 100.0 + " consists of");
		System.out.println(" " + numberOfOneDollars + " dollars");
		System.out.println(" " + numberOfQuarters + " quarters");
		System.out.println(" " + numberOfDimes + " dimes");
		System.out.println(" " + numberOfNickels + " nickels");
		System.out.println(" " + numberOfPennies + " pennies");
	}
}
