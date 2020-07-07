package Exercise_08._06;

import java.util.Scanner;

public class MultiplyTwoMatrices {// Yl and github
    public static void main(String[] args) {
        // Prompt the user to enter two matrices
        double[][] a = userInput( 1 );
        double[][] b = userInput( 2 );


        // Call methods
        double[][] multiply = multiplyMatrix( a, b);

        // Print result
        print(a, b, multiply);
    }

    /** Enter elements in matrix */
    public static double[][] userInput(int n) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a
        System.out.print( "Enter matrix" + n + ": " );

        double[][] array = new double[3][3];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                array[i][j] = input.nextDouble();
        }

        return array;
    }

    /** Add to matrices and store the value in new 2D array then return it */
    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        double[][] multiply = new double[a.length][b.length];

        for (int i = 0; i < multiply.length; i++)
            for (int j = 0; j < multiply[i].length; j++)
                multiply[i][j] =
                        (int)(10 * (a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j]))/10.0;

        return multiply;
    }


    /** Print result */
    public static void print(double[][] a, double[][] b, double[][] sum) {
        System.out.println("The multiplication of the matrices is");

        for (int row = 0; row < 3; row++) {
            printOneRow( a[row] );

            System.out.print((row == 1) ? " +  ": "    ");

            printOneRow( b[row] );

            System.out.print((row == 1) ? " =  ": "    ");

            printOneRow( sum[row] );

            System.out.println();
        }
    }

    /** Display just on row of matrix */
    public static void printOneRow(double[] array) {
        for (double aDouble : array)
            System.out.print( aDouble + " " );
    }
}
