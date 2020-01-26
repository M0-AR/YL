package Exercise_04._26;

import java.util.Scanner;

public class MonetaryUnits {
    public static void main(String[] args) { // g
        Scanner input = new Scanner( System.in );

        // Receive the amount
        System.out.print( "Enter an amount: " );
        String amountString = "11.56";// = input.nextLine(); TODO

        int decimalPosition = amountString.indexOf( '.' );

        int amount; // amount in cents

        if (decimalPosition == -1) {
            amount = Integer.parseInt( amountString );
        } else {
            String fractionPart = amountString.substring( decimalPosition + 1 ); // +1 because to not take . with

            if (fractionPart.length() >= 2) {
                fractionPart = fractionPart.substring( 0, 2 );
            } else if (fractionPart.length() == 1) {
                fractionPart += "0";
            } else {
                fractionPart += "00";
            }

            amount = Integer.parseInt(
                    amountString.substring( 0, decimalPosition ) + fractionPart );
        }

        //--------------- OR
//        String numberOfOneDollars = amountString.substring( 0, amountString.indexOf( '.' ) );
//        int cents = Integer.parseInt(amountString.substring( amountString.indexOf( '.' ) +1));
// Find the number of quarters
//        int numberOfQuarters = cents / 25;
//        cents %= 25;......


        // Convert the amount into cents
        int remainingAmount = amount;

        // Find the number of dollars in the remaining amount
        int numberOfOneDollars = remainingAmount / 100;
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

        // Find the number of pennies in the remaining amount
        int numberOfPennies = remainingAmount;

        // Display results
        System.out.println( "Your amount " + amountString + " consists of \n" +
                "\t" + numberOfOneDollars + " dollars\n" +
                "\t" + numberOfQuarters + " quarters\n" +
                "\t" + numberOfDimes + " dimes\n" +
                "\t" + numberOfNickels + " nickels\n" +
                "\t" + numberOfPennies + " pennies" );
    }
}
