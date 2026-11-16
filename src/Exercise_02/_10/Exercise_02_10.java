// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._10;

import java.util.Scanner;

public class Exercise_02_10 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter the amount of water in kilograms: ");
		double kilograms = input.nextDouble();

		System.out.print("Enter the initial temperature: ");
		double initialTemperature = input.nextDouble();

		System.out.print("Enter the final temperature: ");
		double finalTemperature = input.nextDouble();


		double energy = kilograms * (finalTemperature - initialTemperature) * 4184;


		System.out.println("The energy needed is " + energy);
	}
}
