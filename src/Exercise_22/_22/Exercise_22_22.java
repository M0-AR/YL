package Exercise_22._22;

import java.util.Scanner;

public class Exercise_22_22 {
    public static void main(String[] args) {
        int[][] grid = readGrid(new Scanner(System.in));
        if (solve(grid)) {
            printGrid(grid);
        } else {
            System.out.println("No solution");
        }
    }

    private static int[][] readGrid(Scanner input) {
        int[][] grid = new int[9][9];
        StringBuilder digits = new StringBuilder();
        while (digits.length() < 81 && input.hasNext()) {
            for (char ch : input.next().toCharArray()) {
                if (Character.isDigit(ch)) {
                    digits.append(ch);
                }
            }
        }
        for (int i = 0; i < 81 && i < digits.length(); i++) {
            grid[i / 9][i % 9] = digits.charAt(i) - '0';
        }
        return grid;
    }

    private static boolean solve(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (grid[row][column] != 0) {
                    continue;
                }
                for (int value = 1; value <= 9; value++) {
                    if (isValid(grid, row, column, value)) {
                        grid[row][column] = value;
                        if (solve(grid)) {
                            return true;
                        }
                        grid[row][column] = 0;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isValid(int[][] grid, int row, int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value || grid[i][column] == value) {
                return false;
            }
        }
        int startRow = row / 3 * 3;
        int startColumn = column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startColumn + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int value : row) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
}
