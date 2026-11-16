// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._02;

import java.util.Scanner;

public class Exercise_02_02 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		final double PI = 3.14159265359;


		System.out.print("Enter the radius and length of a cylinder: ");
		double radius = input.nextDouble();
		double length = input.nextDouble();


		double area = radius * radius * PI;
		double volume = area * length;


		System.out.println("The area is " + area);
		System.out.println("The volume is " + volume);
	}
}
