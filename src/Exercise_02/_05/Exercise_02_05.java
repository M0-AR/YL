// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._05;

import java.util.Scanner;

public class Exercise_02_05 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);


		System.out.print("Enter the subtotal and a gratuity rate: ");
		double subtotal = input.nextDouble();
		double gratuityRate = input.nextDouble();


		double gratuity = subtotal * (gratuityRate / 100);
		double total = subtotal + gratuity;


		System.out.println("The gratuity is $" + gratuity +
			" and total is $" + total);
	}
}
