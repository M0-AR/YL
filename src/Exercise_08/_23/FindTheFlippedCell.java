package Exercise_08._23;

import java.util.Scanner;

public class FindTheFlippedCell {
    public static void main(String[] args) {
/*
        int[][] matrixForTest =  {
                { 1, 1, 1, 0, 1, 1},
                { 1, 1, 1, 1, 0, 0},
                { 0, 1, 0, 1, 1, 1},
                { 1, 1, 1, 1, 1, 1},
                { 0, 1, 1, 1, 1, 0},
                { 1, 0, 0, 0, 0, 1},
        };
*/
        int[][] matrix = initialize6By6MatrixByUserWithValues0Or1();


        // Print output
        int r = isEveryRowHasEvenNumberOf1s( matrix );
        int c = isEveryColumnHasEvenNumberOf1s( matrix );
        if (r != -1 || c != -1)
            System.out.println("The flipped cell is at (" + r + "," + c + ")");

    }

    private static int[][] initialize6By6MatrixByUserWithValues0Or1() {
        Scanner input = new Scanner( System.in );
        int[][] _6By6Array = new int[6][6];

        System.out.println("Enter a 6-by-6 matrix row by row: ");
        for (int row = 0; row < _6By6Array.length; row++) {
            for (int column = 0; column < _6By6Array[row].length; column++) {
                _6By6Array[row][column] = input.nextInt();
            }
        }
        return _6By6Array;
    }

    private static int isEveryRowHasEvenNumberOf1s(int[][] matrix) {
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 1)
                    count++;
            }
            if (count % 2 != 0)
                return row;
        }
        return -1;
    }

    private static int isEveryColumnHasEvenNumberOf1s(int[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            int sum = 0;
            for (int row = 0; row < matrix.length; row++)
                sum += matrix[row][column];
            if (sum % 2 != 0)
                return column;
        }
        return -1;
    }
}
