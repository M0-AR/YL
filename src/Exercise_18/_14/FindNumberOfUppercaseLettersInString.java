package Exercise_18._14;

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
        // Storing the numbers of uppercase letters
        int number = 0;
        // Base case
        if (s.length() > 0) {
            number = numberOfUppercaseLetters( s.substring( 1 ) ) +
                    (Character.isUpperCase( s.charAt( 0 ) ) ? 1 : 0);
        }
        return number;
    }
}
