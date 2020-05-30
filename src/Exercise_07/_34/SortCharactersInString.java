package Exercise_07._34;
/*********************************************************************************
 * (Sort characters in a string) Write a method that returns a sorted string      *
 * using the following header:                                                    *
 *                                                                                *
 * public static String sort(String s)                                            *
 *                                                                                *
 * For example, sort("acb") returns abc.                                          *
 * Write a test program that prompts the user to enter a string and displays the  *
 * sorted string.                                                                 *
 *********************************************************************************/
import java.util.Scanner;

public class SortCharactersInString {
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();

        // Display string in increasing order
        System.out.println(sort( s ));
    }

    /** Sort string in increasing order */
    public static String sort(String s) {
        // Split s into char array
        char[] splitString = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
            splitString[i] = s.charAt( i );

        // Sort array
        for (int i = 0; i < s.length(); i++) {
            char min = splitString[i];
            int minIndex = i;

            for (int j = i + 1; j < s.length(); j++) {
                if (min > splitString[j]) {
                    min = splitString[j];
                    minIndex = j;
                }
            }

            // Swap
            if (minIndex != i) {
                splitString[minIndex] = splitString[i];
                splitString[i] = min;
            }

        }

        // Merge the array of char into string
        s = "";
        for (int i = 0; i < splitString.length; i++)
            s += splitString[i];

        return s;
    }
}
