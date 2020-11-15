package Exercise_08._27;

import java.util.Scanner;
// https://www.geeksforgeeks.org/sorting-2d-array-according-values-given-column-java/
public class ColumnSorting { // Yl and Github
    public static void main(String[] args) {
        /* White box testing */
        whiteBoxTest();

        double[][] matrix = getMatrix();
        sortColumns( matrix );
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

        double[][] columnSortingTest = sortColumns( original );
        System.out.println("The original array is");
        displayMatrix( original );
        System.out.println("The row-sorted array is");
        displayMatrix( columnSortingTest );
    }


    public static double [][] sortColumns(double[][] m) {
        double[][] result = new double[m.length][m[0].length];

        copyMatrixFromOriginalToCopy(m, result);

        double[] columnArray = new double[m.length];
        for (int column = 0; column < m.length; column++) {

            // Get unsorted column
            for (int row = 0; row < m.length ; row++) {
                columnArray[row] = result[row][column];
            }

            // Sort column
            java.util.Arrays.sort( columnArray );

            // Post sorted column
            for (int row = 0; row < m.length ; row++) {
                result[row][column] = columnArray[row];
            }

        }


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
