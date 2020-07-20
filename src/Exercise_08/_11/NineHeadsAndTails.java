package Exercise_08._11;

import java.util.Scanner;

public class NineHeadsAndTails {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a number
        System.out.print( "Enter a number between 0 and 511: " );
        int number = input.nextInt();

        // Get binary array, which convert parameter's number to binary
        int[] binary = convertToBinary( number );

        // Display result
        printHOrT(binary); // Print H or T according to binary array, since 0 stands for H and 1 for T
    }

    /** Convert number to binary and store them in integer array*/
    public static int[] convertToBinary(int i) {
        // Create an array with length 9
        int[] binary = new int[9];
        for (int j = 0; j < 9; j++)
        {
            if (i != 0) {
                binary[j] = i % 2;
                i >>= 1; // Divide by two in more efficient way
            }
            else
            {
                binary[j] = 0;
            }
        }
        return binary;
    }

    /** Display H or T according to array data, since H stands for 0 and T for 1 */
    public static void printHOrT(int[] array) {
        for (int i = array.length-1; i >= 0; i--) {
            System.out.print(array[i] == 0 ? "H " : "T ");
            if (i % 3 == 0)
                System.out.println();
        }
    }
}
