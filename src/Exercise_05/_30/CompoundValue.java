package Exercise_05._30;
/*
(Financial application: compound value) Suppose you save $100 each month
into a savings account with the annual interest rate 5%. So, the monthly interest
rate is 0.05 / 12 = 0.00417. After the first month, the value in the account becomes
					100 * (1 + 0.00417) = 100.417
After the second month, the value in the account becomes
					(100 + 100.417) * (1 + 0.00417) = 201.252
After the third month, the value in the account becomes
					(100 + 201.252) * (1 + 0.00417) = 302.507
and so on.
Write a program that prompts the user to enter an amount (e.g., 100), the annual
interest rate (e.g., 5), and the number of months (e.g., 6) and displays the amount
in the savings account after the given month.
*/
import java.util.Scanner;

public class CompoundValue {
    public static void main(String[] args) { // GitHUb and y
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter an amount, interest rate and number of months
        System.out.print( "Enter an amount: " );
        double save = input.nextDouble();

        System.out.print( "Enter an interest: " );
        double interest = input.nextDouble();

        System.out.print( "Enter number of months: " );
        int numberOfMonth = input.nextInt();

        // Find monthlyInterest
        double monthlyInterest = interest / 1200;
        double value = 0;

        // Display value
        for (int i = 1; i <=  numberOfMonth; i++) {
            value = (int)(1000 * (save + value) * (1 + monthlyInterest)) / 1000.0;
            System.out.println(value);
        }

        // Display result
        System.out.printf(
                "Amount in savings account after " +
                        numberOfMonth + " months: $%.2f\n", value);
    }
}
