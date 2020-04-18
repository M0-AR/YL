package Exercise_07._05;
/**
(Print distinct numbers) Write a program that reads in ten numbers and displays
the number of distinct numbers and the distinct numbers separated by exactly one
space (i.e., if a number appears multiple times, it is displayed only once). (Hint:
Read a number and store it to an array if it is new. If the number is already in the
array, ignore it.) After the input, the array contains the distinct numbers.
*/
import java.util.Scanner;

public class PrintDistinctNumbers {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array
        int[] distinctNumbers = new int[10];
        int number;

        // Number of distinct numbers
        int count = 0;

        // Prompt the user to enter 10 numbers
        System.out.print( "Enter 10 numbers: " );
        for (int i = 0; i < distinctNumbers.length; i++){
            number = input.nextInt();

            // Check if distinct!
            if (isDistinct(distinctNumbers, number))
                distinctNumbers[count++] = number;

        }


        displayResult( distinctNumbers, count );
    }

    /** Return true if the number is not in the array */
    public static boolean isDistinct(int[] array, int n){
        for (int i : array)
            if (i == n)
                return false;

        return true;
    }


    static void displayResult(int[] distinct, int length) {
        // Display result
        System.out.print("The number of distinct numbers is " + length
                                    + "\nThe distinct numbers are: ");
        for (int i : distinct)
            if (i > 0)
                System.out.print( i + " " );
    }
}
