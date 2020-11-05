package Exercise_08._25;
/*********************************************************************************
 * (Markov matrix) An n * n matrix is called a positive Markov matrix if each     *
 * element is positive and the sum of the elements in each column is 1. Write the *
 * following method to check whether a matrix is a Markov matrix.                 *
 *                                                                                *
 * public static boolean isMarkovMatrix(double[][] m)                             *
 *                                                                                *
 * Write a test program that prompts the user to enter a 3 * 3 matrix of double   *
 * values and tests whether it is a Markov matrix.                                *
 *********************************************************************************/
import java.util.Scanner;

public class MarkovMatrix {
    public static void main(String[] args) {
        /* Test */
        //whiteBoxTest();

       double[][] matrix = getMatrix();
        System.out.println("It is" + (isMarkovMatrix( matrix ) ? " " : " not ") + "a Markov matrix");
    }


    public static double[][] getMatrix() {
        Scanner input = new Scanner( System.in );

        System.out.println("Enter a 3-by-3 matrix row by row: ");
        double[][] matrix = new double[3][3];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = input.nextDouble();

        return matrix;
    }

    public static boolean isMarkovMatrix(double[][] m) {
      return isElementsPositive( m ) && isEachColumnSum1( m );
    }

    public static boolean isElementsPositive(double[][] m) {
        for (int row = 0; row < m.length; row++) {
            for (int column = 0; column < m[row].length; column++) {
                if (m[row][column] < 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean isEachColumnSum1(double[][] m) {
        for (int column = 0; column < m.length; column++) {
            double sum = 0;
            for (int row = 0; row < m[column].length; row++) {
                sum += m[row][column];
            }
            if (sum != 1)
                return false;
        }
        return true;
    }

    public static void whiteBoxTest() {
          double[][] mMarkovMatrixPositiveTest = {{0.15, 0.875, 0.375},
                                                {0.55, 0.005, 0.225},
                                                {0.30, 0.12,  0.4}};

        double[][] mMarkovMatrixNegativeTest = {{0.95, -0.875, 0.375},
                                                {0.65, 0.005, 0.225},
                                                {0.30, 0.22,  -0.4}};
        System.out.println("Positive test: " + isMarkovMatrix( mMarkovMatrixPositiveTest ));
        System.out.println("Negative test: " + isMarkovMatrix( mMarkovMatrixNegativeTest ));

    }

}
