package Exercise_08._13;

import java.util.Scanner;

public class LocateLargestElement {
    public static void main(String[] args) { // Github
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
        int rowNumber = input.nextInt();
        int columnNumber = input.nextInt();

        // Create an 2D array
        double[][] values = new double[rowNumber][columnNumber];

        // Prompt the user to fill the array
        System.out.println( "Enter the array: " );
        for (int row = 0; row < values.length; row++) {
            for (int column = 0; column < values[row].length; column++) {
                values[row][column] = input.nextDouble();
            }
        }

        return values;
    }

    /** Return indices of largest element in a */
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
