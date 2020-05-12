package Exercise_07._19;
/*********************************************************************************
 * (Sorted?) Write the following method that returns true if the list is already  *
 * sorted in increasing order.                                                    *
 *                                                                                *
 * public static boolean isSorted(int[] list)                                     *
 *                                                                                *
 * Write a test program that prompts the user to enter a list and displays        *
 * whether the list is sorted or not. Here is a sample run. Note that the first   *
 * number in the input indicates the number of the elements in the list. This     *
 * number is not part of the list.                                                *
 *********************************************************************************/
import java.util.Scanner;

public class Sorted {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a length of the array
        System.out.print( "Enter the size of the list: " );
        int length = input.nextInt();

        // Create an array
        int[] list = new int[length];

        // Prompt the user to enter contents of the list
        System.out.print( "Enter the contents of the list: " );
        for(int i = 0; i < list.length; i++)
            list[i] = input.nextInt();

        // Display result
        System.out.print( "The list has " + length + " integers " );
        for (int i : list)
            System.out.print( i + " " );

        System.out.println("\nThe list is" +
                (isSorted( list )? " ": " not ") + "already sorted");




    }

    public static boolean isSorted(int[] list){
        for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1])
                    return false;
        }
        return true;
    }
}
