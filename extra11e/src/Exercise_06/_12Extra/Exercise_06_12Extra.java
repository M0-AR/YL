package Exercise_06._12Extra;

import java.util.Scanner;

public class Exercise_06_12Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter yearly interest rate, for example 8.25: ");
		double annualInterestRate = input.nextDouble();
		System.out.print("Enter number of years as an integer, for example 5: ");
		int numberOfYears = input.nextInt();
		System.out.print("Enter loan amount, for example 120000.95: ");
		double loanAmount = input.nextDouble();

		System.out.printf("The monthly payment is $%.2f%n",
			getMonthlyPayment(annualInterestRate, numberOfYears, loanAmount));
		System.out.printf("The total payment is $%.2f%n",
			getTotalPayment(annualInterestRate, numberOfYears, loanAmount));
	}

	public static double getMonthlyPayment(double annualInterestRate,
		double numberOfYears, double loanAmount) {
		double monthlyInterestRate = annualInterestRate / 1200;
		return loanAmount * monthlyInterestRate
			/ (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
	}

	public static double getTotalPayment(double annualInterestRate,
		double numberOfYears, double loanAmount) {
		return getMonthlyPayment(annualInterestRate, numberOfYears, loanAmount)
			* numberOfYears * 12;
	}
}
