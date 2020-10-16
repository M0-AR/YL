package Exercise_08._24;

import java.util.Scanner;

public class CheckSudokuSolution {


    public static void main(String[] args) {

    }

    public static int[][] readASudoukuSolution() {
        Scanner input = new Scanner( System.in );

        System.out.println("Enter a Sudoku puzzle solution");
        int[][] grid = new int[9][9];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
               grid[i][j] = input.nextInt();

        return grid;
    }


    private static boolean isEveryRowHasNumbers1To9(int[][] matrix) {
        for (int row = 0; row < matrix[0].length; row++) {
            int sum = 0;
            for (int column = 0; column < matrix.length; column++)
                sum += matrix[row][column];
            if (sum != 45)
                return false;
        }
        return true;
    }

    private static boolean isEveryColumnHasNumbers1To9(int[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            int sum = 0;
            for (int row = 0; row < matrix.length; row++)
                sum += matrix[row][column];
            if (sum != 45)
                return false;
        }
        return true;
    }


    private static boolean isEverySmallBoxHasNumbers1To9(int[][] matrix) {
        int sum = 0;
        // todo check every 3 by 3 box if equal to 45
    }




}



