package Exercise_18._09;

import java.util.Scanner;

/*********************************************************************************
 * (Print the characters in a string reversely) Write a recursive method that     *
 * displays a string reversely on the console using the following header:         *
 *                                                                                *
 * public static void reverseDisplay(String value)                                *
 *                                                                                *
 * For example, reverseDisplay("abcd") displays dcba. Write a test program that   *
 * prompts the user to enter a string and displays its reversal.                  *
 *********************************************************************************/
public class StringReversely {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.print("Enter a string: ");
        String value = input.next();

        // Display the value reversely
        reverseDisplay(value);

    }

    public static void reverseDisplay(String value){
        if (value.length() == 1){
            System.out.print(value);
        }else {
            System.out.print(value.charAt( value.length() -1 ));
            reverseDisplay( value.substring( 0, value.length()-1 ) );
        }

    }

}
