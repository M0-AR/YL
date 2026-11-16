// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_02._21;

import java.util.Scanner;

public class Exercise_02_21 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);



		System.out.print("Enter investment amount: ");
		double amount = input.nextDouble();
		System.out.print("Enter annual interest rate in percentage: ");
		double monthlyInterestRate = input.nextDouble();
		monthlyInterestRate /= 1200;
		System.out.print("Enter number of years: ");
		int years = input.nextInt();


		double futureInvestmentValue =
			amount * Math.pow(1 + monthlyInterestRate, years * 12);


		System.out.println("Accumulated value is $" + futureInvestmentValue);
	}
}
