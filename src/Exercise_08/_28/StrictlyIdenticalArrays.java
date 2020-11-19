package Exercise_08._28;

import java.util.Scanner;

public class StrictlyIdenticalArrays {
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
        int[][] list1 = {{51, 22, 25}, {6, 1, 4}, {24, 54, 6}};
        int[][] list2 = {{51, 22, 25}, {6, 1, 4}, {24, 54, 6}};
        System.out.println("The two arrays are" +
                (equals(list1, list2) ? " " : " not ") +
                "strictly identical");

        // Negative test
        list1 = new int[][]{{51, 25, 22}, {6, 1, 4}, {24, 54, 6}};
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
    private static boolean equals(int[][] list1, int[][] list2) {
        for (int row = 0; row < MATRIX_LENGTH; row++) {
            for (int column = 0; column < MATRIX_LENGTH; column++) {
                if (list1[row][column] != list2[row][column])
                    return false;
            }
        }
        return true;
    }


}
