package Exercise_08._02;

import java.util.Scanner;

public class SumDiagonalInMatrix {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create a matrix array 4-by-4
        double[][] m = new double[4][4];

        // Prompt the user to enter a 4-by-4 matrix
        System.out.println( "Enter a 4-by-4 matrix row by row:" );
        for (int i = 0; i < m.length ; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = input.nextDouble();
            }
        }


        // Display result
            System.out.println("Sum of the elements at in the major "
                                + "diagonal is " + sumMajorDiagonal( m));
    }

    public static double sumMajorDiagonal(double[][] m){
        double sum = 0;
        for (int i = 0; i < m.length ; i++) {
            sum += m[i][i];
        }
        return sum;
    }
}
