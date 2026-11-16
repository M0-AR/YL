// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._17;

import java.util.Scanner;

public class Exercise_02_17 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);



		System.out.print("Enter the temperature in Fahrenheit " +
			"between -58ºF and 41ºF: ");
		double temperature = input.nextDouble();
		System.out.print("Enter the wind speed (>= 2) in miles per hour: ");
		double speed = input.nextDouble();


		double windChill = 35.74 + 0.6215 * temperature -
								 35.75 * Math.pow(speed, 0.16) +
								 0.4275 * temperature * Math.pow(speed, 0.16);


		System.out.println("The wind chill index is " + windChill);
	}
}
