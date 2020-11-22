package Exercise_08._29;

import java.util.Scanner;

public class IdenticalArrays { // yl and github
    private static final int MATRIX_LENGTH = 3;
    public static void main(String[] args) {
        /* White box testing */
        whiteBoxTest();

        int[][] list1 = getMatrix(1);
        int[][] list2 = getMatrix( 2 );

        System.out.println("The two arrays are" +
                (equals(list1, list2) ? " " : " not ") +
                "strictly identical");
    }


    public static void whiteBoxTest() {
        // Positive test
        int[][] list1 = {{51, 25, 22}, {6, 1, 4}, {24, 54, 6}};
        int[][] list2 = {{51, 22, 25}, {6, 1, 4}, {24, 54, 6}};
        System.out.println("The two arrays are" +
                (equals(list1, list2) ? " " : " not ") +
                "strictly identical");

        // Negative test
        list1 = new int[][]{{51, 5, 22}, {6, 1, 4}, {24, 54, 6}};
        list2 = new int[][]{{51, 22, 25}, {6, 1, 4}, {24, 54, 6}};
        System.out.println("The two arrays are" +
                (equals(list1, list2) ? " " : " not ") +
                "strictly identical");
    }

    private static int[][] getMatrix(int listNumber) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter list" + listNumber + ": ");
        int[][] matrix = new int[MATRIX_LENGTH][MATRIX_LENGTH];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = input.nextInt();

        return matrix;
    }
    private static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length * m1[0].length != m2.length * m2[0].length)
            return false;

        for (int row = 0; row < MATRIX_LENGTH; row++) {
            int sumOfM1 = 0;
            int sumOfM2 = 0;
            for (int column = 0; column < MATRIX_LENGTH; column++) {
               sumOfM1 += m1[row][column];
               sumOfM2 += m2[row][column];
            }
            if (sumOfM1 != sumOfM2)
                return false;
        }
        return true;
    }


}
