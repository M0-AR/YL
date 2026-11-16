// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._03;

import java.util.Scanner;

public class Exercise_02_03 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		final double METERS_PER_FOOT = 0.305;


		System.out.print("Enter a value for feet: ");
		double feet = input.nextDouble();


		double meters = feet * METERS_PER_FOOT;


		System.out.println(feet + " feet is " + meters + " meters");
	}
}
