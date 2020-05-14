package Exercise_08._01;

import java.util.Scanner;

public class SumElementsColumnByColumn {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create a matrix array 3-by-4
        double[][] m = new double[3][4];

        // Prompt the user to enter a 3-by-4 matrix
        System.out.println( "Enter a 3-by-4 matrix row by row:" );
        for (int i = 0; i < m.length ; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = input.nextDouble();
            }
        }


        // Display result
        for (int i = 0; i < m[0].length ; i++) {
            System.out.println("Sum of the elements at column: "
                                        + i + " is " + sumColumn( m, i ));
        }

    }

    /** Returns the sum of all
     * elements in a specified column*/
    public static double sumColumn(double [][] m, int columnIndex){
        double sum = 0;
        for (int i = 0; i < m.length ; i++) {
            sum += m[i][columnIndex];
        }
        return sum;
    }
}
