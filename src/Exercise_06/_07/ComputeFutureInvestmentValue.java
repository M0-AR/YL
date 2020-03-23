package Exercise_06._07;

import java.util.Scanner;

/*
(Financial application: compute the future investment value) Write a method that
computes future investment value at a given interest rate for a specified number
of years. The future investment is determined using the formula in Programming
Exercise 2.21.
Use the following method header:
public static double futureInvestmentValue(
	double investmentAmount, double monthlyInterestRate, int years)
For example, futureInvestmentValue(10000, 0.05/12, 5) returns
12833.59.
Write a test program that prompts the user to enter the investment amount (e.g.,
1000) and the interest rate (e.g., 9%) and prints a table that displays future value
for the years from 1 to 30,
*/
public class ComputeFutureInvestmentValue {
    public static void main(String[] args) { // My answer is correct but how can I use years parameter github
        Scanner input = new Scanner( System.in );
        final int NUMBER_OF_YEARS = 30;

        System.out.print("Enter invested amount: ");
        double amount = input.nextDouble();
        System.out.print( "Enter annual interest rate: " );
        double annualInterestRate = input.nextDouble();

        // Get monthly interest rate
        double monthlyInterestRate = annualInterestRate/1200;


        System.out.printf( "%s %20s\n", "Years", "Future Value" );
        for (int i = 1; i <= NUMBER_OF_YEARS  ; i++) {
            amount = futureInvestmentValue( amount, 9, 30 );
            System.out.printf( "%d %15s %.2f\n", i," ", amount);
        }


    }

    public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years){
        return investmentAmount * Math.pow( 1 + monthlyInterestRate, years*12 );
    }
}
