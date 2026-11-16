// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._06;

import java.util.Scanner;

public class Exercise_02_06 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter a number between 0 and 1000: ");
		int number = input.nextInt();


		int lessThan10 = number % 10;
		number /= 10;
		int tens = number % 10;
		number /= 10;
		int hundreds = number % 10;
		number /= 10;
		int sum = hundreds + tens + lessThan10;


		System.out.println("The sum of the digits is " + sum);
	}
}
