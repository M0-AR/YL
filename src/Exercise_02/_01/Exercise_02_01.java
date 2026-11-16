// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._01;

import java.util.Scanner;

public class Exercise_02_01 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a degree in Celsius: ");
		double celsius = input.nextDouble();


		double fahrenheit = 9.0 / 5 * celsius + 32;


		System.out.println(celsius + " Celsius is " + fahrenheit
			+ " Fahrenheit");
	}
}
