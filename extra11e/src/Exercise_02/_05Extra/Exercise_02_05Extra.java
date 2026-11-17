package Exercise_02._05Extra;

import java.util.Scanner;

public class Exercise_02_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter final account value: ");
		double finalValue = input.nextDouble();
		System.out.print("Enter annual interest rate in percent: ");
		double annualRate = input.nextDouble();
		System.out.print("Enter number of years: ");
		int years = input.nextInt();

		double monthlyRate = annualRate / 1200;
		double initialDeposit = finalValue
			/ Math.pow(1 + monthlyRate, years * 12);

		System.out.println("Initial deposit value is " + initialDeposit);
	}
}
