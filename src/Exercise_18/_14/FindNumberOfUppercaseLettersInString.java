package Exercise_18._14;
/*********************************************************************************
 * (Find the number of uppercase letters in a string) Write a recursive method to *
 * return the number of uppercase letters in a string. Write a test program that  *
 * prompts the user to enter a string and displays the number of uppercase        *
 * letters in the string.                                                         *
 *********************************************************************************/

import java.util.Scanner;

public class FindNumberOfUppercaseLettersInString {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter a string: " );
        String s = input.nextLine();
        System.out.print( "The number of uppercase letters in  " + s +
                " is " + numberOfUppercaseLetters( s ) );

    }

    public static int numberOfUppercaseLetters(String s) {
        return numberOfUppercaseLetters( s, s.length() - 1 );
    }


    public static int numberOfUppercaseLetters(String s, int high) {
        // Base case
        if (high < 0) {
            return 0;
        }
        return numberOfUppercaseLetters( s.substring( 1 ) ) +
                (Character.isUpperCase( s.charAt( 0 ) ) ? 1 : 0);
    }
}
