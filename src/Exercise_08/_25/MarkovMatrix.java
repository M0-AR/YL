package Exercise_08._25;

import java.util.Scanner;

public class MarkovMatrix {
    public static void main(String[] args) {
        /* Test */
        // whiteBoxTest();

       double[][] matrix = enterAMatrix();
        System.out.println("It is" + (isMarkovMatrix( matrix ) ? " " : " not ") + "a Markov matrix");
    }


    public static double[][] enterAMatrix() {
        Scanner input = new Scanner( System.in );

        System.out.println("Enter a 3-by-3 matrix row by row: ");
        double[][] matrix = new double[3][3];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = input.nextDouble();

        return matrix;
    }

    public static boolean isMarkovMatrix(double[][] m) {
        // Positive Numbers and sum of column is 1
        for (int column = 0; column < m.length; column++) {
            double sum = 0;
            for (int row = 0; row < m[column].length; row++) {
                if (m[row][column] < 0) return false;
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
