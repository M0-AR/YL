package Exercise_06._20;
/*
(Count the letters in a string) Write a method that counts the number of letters in
a string using the following header:
public static int countLetters(String s)
Write a test program that prompts the user to enter a string and displays the number
of letters in the string.
*/
import java.util.Scanner;

public class CountTheLettersInString {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );


        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();


        int n = getNumber( s );
        System.out.println( "The number of letter" + ((n > 1) ? "s" : "") + " in the string is " + n );
    }

    /**
     * Count the number of String.
     */
    public static int getNumber(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter( s.charAt( i ) )) {
                count++;
            }
        }

        return count;
    }
}
