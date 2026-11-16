// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._16;

import java.util.Scanner;

public class Exercise_02_16 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter the side: ");
		double side = input.nextDouble();


		double area = ((3 * Math.pow(3, 0.5)) / 2) * Math.pow(side, 2);


		System.out.println("The area of the hexagon is " + area);
	}
}
