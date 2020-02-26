package Exercise_05._31;
/*
(Financial application: compute CD value) Suppose you put $10,000 into a CD
with an annual percentage yield of 5.75%. After one month, the CD is worth
				10000 + 10000 * 5.75 / 1200 = 10047.92
After two months, the CD is worth
				10047.91 + 10047.91 * 5.75 / 1200 = 10096.06
After three months, the CD is worth
				10096.06 + 10096.06 * 5.75 / 1200 = 10144.44
and so on.
Write a program that prompts the user to enter an amount (e.g., 10000), the
annual percentage yield (e.g., 5.75), and the number of months (e.g., 18) and
displays a table as shown in the sample run.
*/
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
            System.out.printf( "\n %d %14.2f ", i, amount );
        }
    }
}
