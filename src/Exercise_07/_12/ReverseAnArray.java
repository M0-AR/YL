package Exercise_07._12;

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

        reverseArray( d );


    }


    /** To reverse and display the array */
    public static void reverseArray(int[] array ){
        // Display result
        for (int i = array.length-1; i >= 0 ; i--) {
            System.out.print( array[i] + " " );
        }
    }
}
