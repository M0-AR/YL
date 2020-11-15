package Exercise_08._26;
/*********************************************************************************
 * (Row sorting) Implement the following method to sort the rows in a             *
 * two-dimensional array. A new array is returned and the original array is       *
 * intact.                                                                        *
 *                                                                                *
 * public static double[][] sortRows(double[][] m)                                *
 *                                                                                *
 * Write a test program that prompts the user to enter a 3 * 3 matrix of double   *
 * values and displays a new row-sorted matrix.                                   *
 *********************************************************************************/

import java.util.Scanner;

public class RowSorting {
    public static void main(String[] args) {
        /* White box testing */
         // whiteBoxTest();

        double[][] matrix = getMatrix();
        sortRows( matrix );
        System.out.println("The row-sorted array is");
        displayMatrix( matrix );
    }

    public static double[][] getMatrix() {
        Scanner input = new Scanner( System.in );

        System.out.println("Enter a 4-by-4 matrix row by row: ");
        double[][] matrix = new double[4][4];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = input.nextDouble();

        return matrix;
    }

    public static void whiteBoxTest() {
        double[][] original = {{0.15, 0.875, 0.375, 0.225},
                {0.55, 0.005, 0.225, 0.015},
                {0.30, 0.12,  0.4, 0.008},
                {0.07, 0.021, 0.14, 0.2}};

        double[][] rowSortingTest = sortRows( original );
        System.out.println("The original array is");
        displayMatrix( original );
        System.out.println("The row-sorted array is");
        displayMatrix( rowSortingTest );
    }


    public static double [][] sortRows(double[][] m) {
        double[][] result = new double[m.length][m[0].length];

        copyMatrixFromOriginalToCopy(m, result);

        for (int row = 0; row < m.length; row++)
              java.util.Arrays.sort( result[row] );

        return result;
    }

    private static void copyMatrixFromOriginalToCopy(double[][] original, double[][] copy) {
        for (int i = 0; i < original.length; i++)
            for (int j = 0; j < original[0].length; j++)
                copy[i][j] = original[i][j];
    }



    public static void displayMatrix(double[][] m) {
        for (double[] doubles : m) {
            for (int i = 0; i < doubles.length; i++) {
                System.out.print(doubles[i] +
                        ((i != doubles.length-1) ? ", " : ""));
            }
            System.out.println();
        }
    }
}
