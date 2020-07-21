package Exercise_08._11;
/** (Game: nine heads and tails) Nine coins are placed in a 3-by-3 matrix with some
 * face up and some face down. You can represent the state of the coins using a
 * 3-by-3 matrix with values 0 (heads) and 1 (tails). Here are some examples:
 *
 *  0 0 0          1 0 1        1 1 0       1 0 1           1 0 0
 *  0 1 0          0 0 1        1 0 0       1 1 0           1 1 1
 *  0 0 0          1 0 0        0 0 1       1 0 0           1 1 0
 *
 * Each state can also represented using a binary number. For example, the pre-
 * ceding matrices correspond to the numbers
 *
 * 000010000   101001100     110100001      101110100       100111110
 *
 * There are a total of 512 possibilities, so you can use decimal numbers 0, 1, 2, 3, ...,
 * and 511 to represent all states of the matrix. Write a program that prompts the
 * user to enter a number between 0 and 511 and displays th corresponding matrix
 * with characters H and T. In the following sample run, the user entered 7, which
 * corresponds to 000000111. Since 0 stands for H and 1 for T, the output is correct.
 *
 * Enter a number between 0 and 511: 7
 *
 *  H H H
 *  H H H
 *  T T T
 **/
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
