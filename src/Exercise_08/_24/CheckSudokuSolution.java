package Exercise_08._24;

import java.util.Scanner;

public class CheckSudokuSolution {


    public static void main(String[] args) { // Todo isEverySmallBoxHasNumbers1To9 just read one box
                                                // Make one method that call the rest of methods
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



