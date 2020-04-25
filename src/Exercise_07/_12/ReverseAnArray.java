package Exercise_07._12;

/*********************************************************************************
 * (Reverse an array) The reverse method in Section 7.7 reverses an array by      *
 * copying it to a new array. Rewrite the method that reverses the array passed   *
 * in the argument and returns this array. Write a test program that prompts the  *
 * user to enter ten numbers, invokes the method to reverse the numbers, and      *
 * displays the numbers.                                                          *
 *********************************************************************************/
import java.util.Arrays;
import java.util.Scanner;

public class ReverseAnArray {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array of length 10
        int[] d = new int[10];

        // Prompt the user to enter ten double values
        System.out.print("Enter 10 digits number: ");
        for (int i = 0; i < 10 ; i++)
            d[i] = input.nextInt();


        // Invoke the method to reverse the numbers
        reverseArray( d);

                // Display the reversal array
        System.out.println("The reversal of the input is \n" +
                Arrays.toString(d));


    }


    /** To reverse and display the array */
    public static void reverseArray(int[] array ){

        for (int i = array.length-1, j = 0; i >= array.length / 2  ; i--, j++) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

    }
}
