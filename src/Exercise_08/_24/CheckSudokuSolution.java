package Exercise_08._24;

/**
 * (Check Sudoku solution) Listing 8.4 checks whether a solution is valid by checking whether every number is valid in the board. Rewrite the program by checking
 *  whether every row, every column, and every small box has the numbers 1 to 9.
 */

import java.util.Scanner;
public class CheckSudokuSolution {


    public static void main(String[] args) { // Yl and github
/*
        int [][] sudoukuTest = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {3, 1, 2, 8, 4, 5, 9, 6, 7},
                {6, 9, 7, 3, 1, 2, 8, 4, 5},
                {8, 4, 5, 6, 9, 7, 3, 1, 2},
                {2, 3, 1, 5, 7, 4, 6, 9, 8},
                {9, 6, 8, 2, 3, 1, 5, 7, 4},
                {5, 7, 4, 9, 6, 8, 2, 3, 1},

        };
*/
        int[][] grid = readASudoukuSolution();
        System.out.println((isEveryRowHasNumbers1To9( grid ) &&
                            isEveryColumnHasNumbers1To9( grid ) &&
                            isEverySmallBoxHasNumbers1To9( grid )) ?
                                "Valid solution" : "Invalid solution");

        System.out.println();
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
        for (int i = 3; i <= 9; i+=3) {
            for (int j = 3; j <= 9; j+=3) {
                int sum = 0;
                for (int row = i-3; row < i; row++) {
                    for (int column = j-3; column < j; column++) {
                        sum += matrix[row][column];
                    }
                }
                if (sum != 45)
                    return false;
            }
        }
       return true;
    }


    private static boolean isEverySmallBoxHasNumbers1To9_(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int sum = 0;
                for (int row = i * 3; row < i * 3 + 3 ; row++) {
                    for (int column = j * 3; column < j * 3 + 3; column++) {
                        sum += matrix[row][column];
                    }
                }
                if (sum != 45)
                    return false;
            }
        }
        return true;
    }

}



