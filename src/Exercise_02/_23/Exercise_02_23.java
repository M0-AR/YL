// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._23;

import java.util.Scanner;

public class Exercise_02_23 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);



		System.out.print("Enter the driving distace: ");
		double distace = input.nextDouble();
		System.out.print("Enter miles per gallon: ");
		double milesPerGallon = input.nextDouble();
		System.out.print("Enter price per gallon: ");
		double pricePerGallon = input.nextDouble();


		double costOfDriving = (distace / milesPerGallon) * pricePerGallon;


		System.out.println("The cost of driving is $" + costOfDriving);
	}
}
