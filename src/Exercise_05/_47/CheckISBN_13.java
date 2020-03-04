package Exercise_05._47;

import java.util.Scanner;

public class CheckISBN_13 {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a ISBN
        System.out.println( "Enter the first 12 digits of an ISBN-13 as a string: : " );
        String isbn = input.nextLine();


        if (isbn.length() != 12) {
            System.out.println( isbn + " is an invalid input" );
            System.exit( 1 );
        }

        // Compute the mean of 12 digit numbers
        int mean = 0;
        for (int i = 0; i < isbn.length(); i++) {
            mean += i % 2 == 0 ? 3 * Integer.parseInt( isbn.charAt( i ) + "" ) :
                    Integer.parseInt( String.valueOf( isbn.charAt( i ) ) );
        }

        // Compute last last digit of ISBN
        int lastDigit = 10 - mean % 10;

        // Display result
        System.out.println( "The ISBN-13 number is " + isbn + ((lastDigit == 10) ? "0" : lastDigit) );

    }
}
