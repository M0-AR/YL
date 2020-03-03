package Exercise_05._47;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckISBN_13 {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        String s = "978013213080"; // todo eror with output 8 insted of 6
        s = "978013213079";

        if (s.length() < 12) {
            System.out.println( s + " is an invalid input" );
            System.exit( 0 );
        }

        // Compute the mean of 12 digit numbers
        int mean = 0;
        for (int i = 0; i < s.length(); i++) {
            mean += Integer.parseInt( String.valueOf( s.charAt( i ) ) );
        }

        // Compute last last digit of ISBN
        int lastDigit = 10 - mean % 10;

        // Display result
        System.out.println( "The ISBN-13 number is " + s + ((lastDigit == 10) ? "0" : lastDigit) );

    }
}
