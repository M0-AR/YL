package Exercise_05._31;

import java.util.Scanner;

public class ComputeCDValue {
    public static void main(String[] args) { // github
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter an amount, interest rate and number of months
        System.out.print( "Enter the initial deposit amount: " );
        double amount = input.nextDouble();

        System.out.print( "Enter annual percentage yield: " );
        double annual_percentage = input.nextDouble();

        System.out.print( "Enter maturity period (number of months): " );
        int numberOfMonth = input.nextInt();


        // Compute and Displya
        System.out.printf( "%s %10s", "Month", "CD Value" );
        for (int i = 1; i <= numberOfMonth ; i++) {
            amount += amount * annual_percentage / 1200;
            System.out.printf( "%s %d %14.2f ","\n", i, amount );
        }
    }
}
