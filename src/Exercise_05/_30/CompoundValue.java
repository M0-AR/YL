package Exercise_05._30;

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


    }
}
