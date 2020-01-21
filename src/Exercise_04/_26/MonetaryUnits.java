package Exercise_04._26;

import java.util.Scanner;

public class MonetaryUnits {
    public static void main(String[] args) { // con page 64
        Scanner input = new Scanner( System.in );

        // Receive the amount
        System.out.print("Enter an amount in double, for example 11.56: ");
        double amount = input.nextDouble();

        // Convert the amount into cents
        int remainingAmount = (int)(amount * 100);

        // Find the number of dollars in the remaining amount
        int numberOfDollars = remainingAmount / 100;
        remainingAmount %= 100;

        // Find the number of dimes in the remaining amount
        int numberOfQuarters = remainingAmount / 25;
        remainingAmount %= 25;

        // Find the number of nickels in the remaining amount
        int numberOfDimes = remainingAmount / 10;
        remainingAmount %= 10;

        // Find the number of pennies in the remaining amount
        int numberOfNickels = remainingAmount / 5;
        remainingAmount %= 5;


    }
}
