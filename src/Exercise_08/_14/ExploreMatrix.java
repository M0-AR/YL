package Exercise_08._14;

import java.util.Scanner;

public class ExploreMatrix {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a the size of matrix
        System.out.print( "Enter the size for the matrix: " );
        int size = input.nextInt();


        // Create two-dimathinal array with length of size
        int[][] zeroOrOne = new int[size][size];

        // Fill 2D array with random 0 or 1
        for (int i = 0; i < zeroOrOne.length; i++)
            for (int j = 0; j < zeroOrOne[i].length; j++)
                zeroOrOne[i][j] = (int)(Math.random() * 2);

        // Print result
        // Row checking
        for (int i = 0; i < zeroOrOne.length; i++) {
            if (checkRow(zeroOrOne[i]))
                System.out.println("All " + (zeroOrOne[i][0] == 0 ? "0":"1")
                                                             + "s on row " + i);
            else
                System.out.println("No same numbers on a row");


        // Column checking
            for (int j = 0; j < zeroOrOne.length; j++) {

            }
        }

        checkColumn(zeroOrOne);
        checkMajorDiagonal(zeroOrOne);
        checkSubDiagonal(zeroOrOne);

    }


}
