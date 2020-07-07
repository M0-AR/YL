package Exercise_08._05;
/*********************************************************************************
 * (Algebra: add two matrices) Write a method to add two matrices. The header of  *
 * the method is as follows:                                                      *
 *                                                                                *
 * public static double[][] addMatrix(double[][] a, double[][] b)                 *
 *                                                                                *
 * In order to be added, the two matrices must have the same dimensions and the   *
 * same or compatible types of elements. Let c be the resulting matrix. Each      *
 * element cij is aij + bij.                                                      *
 * Write a test program that prompts the user to enter two 3 * 3 matrices and     *
 * displays their sum.                                                            *
 *********************************************************************************/
import java.util.Scanner;

public class AddTwoMatrices {

    public static void main(String[] args) {
        // Prompt the user to enter two matrices
        double[][] a = userInput( 1 );
        double[][] b = userInput( 2 );


        // Call methods
        double[][] sum = addMatrix( a, b);

        // Print result
        print(a, b, sum);
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
    public static double[][] addMatrix(double[][] a, double[][] b) {
        double[][] sum = new double[a.length][b.length];

        for (int i = 0; i < sum.length; i++)
            for (int j = 0; j < sum[i].length; j++)
                sum[i][j] = a[i][j] + b[i][j];

        return sum;
    }


    /** Print result */
    public static void print(double[][] a, double[][] b, double[][] sum) {
        System.out.println("The matrices are added as follows");

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
