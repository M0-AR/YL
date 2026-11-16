// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._04;

import java.util.Scanner;

public class Exercise_02_04 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final double KILOGRAMS_PER_POUND = 0.454;


		System.out.print("Enter a number in pounds: ");
		double pounds = input.nextDouble();


		double kilograms = pounds * KILOGRAMS_PER_POUND;


		System.out.println(pounds + " pounds is " + kilograms + " kilograms");
	}
}
