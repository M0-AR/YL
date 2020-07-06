package Exercise_08._05;

import java.util.Scanner;

public class AddTwoMatrices { // Todo check good print

    public static void main(String[] args) {
        // Prompt the user to enter two matrices
        System.out.print( "Enter matrix1: " );
        double[][] a = new double[3][3];
        userInput( a );

        System.out.print( "Enter matrix2: " );
        double[][] b = new double[3][3];
        userInput( b );

        // Call methods
        double[][] sum = addMatrix( a, b);

        // Print result
        System.out.println("The matrices are added as follows");
        print( a );
        System.out.print(" + ");
        print( b );
        System.out.print(" = ");
        print( sum );
    }


    /** Enter elements in matrix */
    public static void userInput(double[][] array) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                array[i][j] = input.nextDouble();

    }

    /** Add to matrices and store the value in new 2D array then return it*/
    public static double[][] addMatrix(double[][] a, double[][] b) {
        double[][] sum = new double[a.length][b.length];

        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[i].length; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }

        return sum;
    }


    /** Display matrix */
    public static void print(double[][] array) {
        for (double[] doubles : array) {
            for (double aDouble : doubles) {
                System.out.print( aDouble + " " );
            }
            System.out.println();
        }
    }
}
