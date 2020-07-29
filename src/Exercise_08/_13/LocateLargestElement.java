package Exercise_08._13;
/*********************************************************************************
 * (Locate the largest element) Write the following method that returns the       *
 * location of the largest element in a two-dimensional array.                    *
 *                                                                                *
 * public static int[] locateLargest(double[][] a)                                *
 *                                                                                *
 * The return value is a one-dimensional array that contains two elements. These  *
 * two elements indicate the row and column indices of the largest element in the *
 * two-dimensional array. Write a test program that prompts the user to enter a   *
 * two-dimensional array and displays the location of the largest element in the  *
 * array.                                                                         *
 *********************************************************************************/
import java.util.Scanner;

public class LocateLargestElement {
    public static void main(String[] args) {
        // Get 2D array from user input
        double[][] values = getUserInput();

        // Invoke locateLargest method and get the row
        // and column indices of the largest element
        int[] rowAndColumnIndices = locateLargest( values );

        // Display result
        System.out.println("The location of the largest element is at " +
                "(" + rowAndColumnIndices[0] + ", " + rowAndColumnIndices[1] + ")");
    }

    /** Prompt the user to enter the length of 2D array and
     *  fill the array with number */
    public static double[][] getUserInput() {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a number of rows and columns
        System.out.print( "Enter tnu number of rows and columns of the array: " );
        int rowLength = input.nextInt();
        int columnLength = input.nextInt();

        // Create an 2D array
        double[][] values = new double[rowLength][columnLength];

        // Prompt the user to fill the array
        System.out.println( "Enter the array: " );
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                values[row][column] = input.nextDouble();
            }
        }

        return values;
    }

    /** Return indices of largest element in a two-dimensional array */
    public static int[] locateLargest(double[][] a) {
        int[] rowAndColumnIndices = new int[2];

        double largestElement = 0;
        for (int row = 0; row < a.length; row++) {
            for (int column = 0; column < a[row].length; column++) {
                if (a[row][column] > largestElement) {
                    largestElement = a[row][column];
                    rowAndColumnIndices[0] = row;
                    rowAndColumnIndices[1] = column;
                }
            }
        }

        return rowAndColumnIndices;
    }
}
