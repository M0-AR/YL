package Exercise_08._26;

import java.util.Arrays;
import java.util.Scanner;

public class RowSorting { // Github and yl
    public static void main(String[] args) {
        /* White box testing */
        // whiteBoxTest();

        double[][] matrix = getMatrix();
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
        double[][] rowSortingTest = {{0.15, 0.875, 0.375, 0.225},
                {0.55, 0.005, 0.225, 0.015},
                {0.30, 0.12,  0.4, 0.008},
                {0.07, 0.021, 0.14, 0.2}};

        sortRows( rowSortingTest );
        displayMatrix( rowSortingTest );
    }


    public static double [][] sortRows(double[][] m) {
        for (int row = 0; row < m.length; row++) {
            m[row] = sortArray( m[row]);
        }
        return m;
    }

    public static double [] sortArray(double[] m) {
        double[] sortingArray = m;
        Arrays.sort( sortingArray );
        return sortingArray;

    }



    public static void displayMatrix(double[][] m) {
        System.out.println("The row-sorted array is");
        for (double[] doubles : m) {
            for (int i = 0; i < doubles.length; i++) {
                System.out.print(doubles[i] + ((i != doubles.length-1) ? ", " : ""));
            }
            System.out.println();
        }
    }
}
