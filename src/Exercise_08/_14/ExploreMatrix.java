package Exercise_08._14;
/*********************************************************************************
 * (Explore matrix) Write a program that prompts the user to enter the length of  *
 * a square matrix, randomly fills in 0s and 1s into the matrix, prints the       *
 * matrix, and finds the rows, columns, and diagonals with all 0s or 1s.          *
 *********************************************************************************/
import java.util.Scanner;

public class ExploreMatrix { // github and yl
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a the size of matrix
        System.out.print( "Enter the size for the matrix: " );
        int size = input.nextInt();


        // Create two-dimensional array with length of size
        int[][] zeroOrOne = new int[size][size];

        // Fill 2D array with random 0 or 1
        for (int i = 0; i < zeroOrOne.length; i++)
            for (int j = 0; j < zeroOrOne[i].length; j++)
                zeroOrOne[i][j] = (int)(Math.random() * 2);
            

        
        // Print result
        for (int[] ints : zeroOrOne) {
            for (int anInt : ints) {
                System.out.print( anInt + " " );
            }
            System.out.println();
        }

        boolean noSameNumber = true;
        // Row checking
        for (int i = 0; i < zeroOrOne.length; i++)
            if (checkRow(zeroOrOne[i])) {
                System.out.println("All " + (zeroOrOne[i][0] == 0 ? "0":"1")
                        + "s on row " + i);
                noSameNumber = false;
            }

        if (noSameNumber)
            System.out.println("No same numbers on a row");



        // Column checking
        noSameNumber = true;
        for (int i = 0; i < zeroOrOne.length; i++) {
            if (checkColumn(zeroOrOne, i)) {
                System.out.println("All " + (zeroOrOne[i][0] == 0 ? "0":"1")
                        + "s on column " + i);
                noSameNumber = false;
            }
        }

        if (noSameNumber)
            System.out.println("No same numbers on a column");



        // MajorDiagonal checking
        if (checkMajorDiagonal(zeroOrOne))
            System.out.println("All " + (zeroOrOne[0][0] == 0 ? "0":"1")
                    + "s on the major diagonal ");
        else
            System.out.println("No same numbers on the major diagonal");



        // Sub-diagonal checking
        if (checkSubDiagonal(zeroOrOne))
            System.out.println("All " + (zeroOrOne[0][3] == 0 ? "0":"1")
                    + "s on the sub-diagonal ");
        else
            System.out.println("No same numbers on the sub-diagonal");


    }

    /** Check if there is a row has same number*/
    public static boolean checkRow(int[] a) {
        for (int i = 0; i < a.length-1; i++)
            if (a[i] != a[i+1])
                return false;
        return true;
    }

    /** Check if there is a column has same number*/
    public static boolean checkColumn(int[][] a, int column) {
        for (int i = 0; i < a.length-1; i++)
            if (a[i][column] != a[i+1][column])
                return false;
        return true;
    }

    /** Check if a major diagonal has same number */
    public static boolean checkMajorDiagonal(int[][] a) {
        for (int i = 0; i < a.length-1; i++)
            if (a[i][i] != a[i+1][i+1])
                return false;
        return true;
    }

    /** Check if a sub diagonal has same number */
    public static boolean checkSubDiagonal(int[][] a) {
        for (int i = 0, j = a.length-1; i < a.length-1; i++, j--)
            if (a[i][j] != a[i+1][j-1])
                return false;
        return true;
    }

}
