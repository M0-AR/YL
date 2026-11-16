// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._12;

import java.util.Scanner;

public class Exercise_02_12 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter speed and acceleration: ");
		double speed = input.nextDouble();
		double acceleration = input.nextDouble();



		double length = Math.pow(speed, 2) / (2 * acceleration);


		System.out.println("The minimum runway length for this" +
								 " airplane is " + length);
	}
}
