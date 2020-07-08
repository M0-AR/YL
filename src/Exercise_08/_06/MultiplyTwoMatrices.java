package Exercise_08._06;
/*********************************************************************************
 * (Algebra: multiply two matrices) Write a method to multiply two matrices. The  *
 * header of the method is:                                                       *
 *                                                                                *
 * public static double[][] multiplyMatrix(double[][] a, double[][] b)            *
 *                                                                                *
 * To multiply matrix a by matrix b, the number of columns in a must be the same  *
 * as the number of rows in b, and the two matrices must have elements of the same*
 * or compatible types. Let c be the result of the multiplication. Assume the     *
 * column size of matrix a is n. Each element cij is ai1 * b1j + ai2 * b2j + ...  *
 * + ain * bnj.                                                                   *
 *                                                                                *
 * Write a test program that prompts the user to enter two 3 * 3 matrices and     *
 * displays their product.                                                        *
 *********************************************************************************/
import java.util.Scanner;

public class MultiplyTwoMatrices {// Yl and github
    public static void main(String[] args) {
        // Prompt the user to enter two matrices
        double[][] a = userInput( 1 );
        double[][] b = userInput( 2 );


        // Call methods
        double[][] multiply = multiplyMatrix( a, b);

        // Print result
        print(a, b, multiply, '*');
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
        double[][] multiply = new double[a.length][b[0].length];

        for (int i = 0; i < multiply.length; i++)
            for (int j = 0; j < multiply[i].length; j++)
                for (int k = 0; k < multiply.length; k++)
                    multiply[i][j] += a[i][k] * b[k][j];



        return multiply;
    }


    /** Print result */
    public static void print(double[][] m1, double[][] m2, double[][] m3, char op) {
        System.out.println("The multiplication of the matrices is");

        for (int row = 0; row < 3; row++) {
            printOneRow( m1[row] );
            System.out.print((row == 1) ? "  " + op + " ": "    ");
            printOneRow( m2[row] );
            System.out.print((row == 1) ? "  = ": "    ");
            printOneRow( m3[row] );
            System.out.println();
        }
    }

    /** Display just on row of matrix */
    public static void printOneRow(double[] array) {
        for (double aDouble : array)
            System.out.printf( "%5.1f", aDouble );
    }
}
