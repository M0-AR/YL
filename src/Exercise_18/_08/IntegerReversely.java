package Exercise_18._08;
/*********************************************************************************
 * (Print the digits in an integer reversely) Write a recursive method that       *
 * displays an int value reversely on the console using the following header:     *
 *                                                                                *
 * public static void reverseDisplay(int value)                                   *
 *                                                                                *
 * For example, reverseDisplay(12345) displays 54321. Write a test program that   *
 * prompts the user to enter an integer and displays its reversal.                *
 *********************************************************************************/
import java.util.Scanner;

public class IntegerReversely {

    public static void main(String[] args) { // github and y
        Scanner input = new Scanner( System.in );
        System.out.print("Enter an integer: ");
        int i = input.nextInt();
        System.out.print("The reversal of " + i + " is ");
        reverseDisplay( i );
    }

    public static void reverseDisplay(int value) {
        // Base case
        if (value != 0) {
            System.out.print( value % 10 ); // To print last digit
            reverseDisplay( value / 10 ); // To reduce value by deleting last digit
        }
    }
}
