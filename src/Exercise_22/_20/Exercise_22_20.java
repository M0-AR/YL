package Exercise_22._20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_22_20 {
    private static int solutionCount;
    private static final List<int[][]> samples = new ArrayList<>();

    public static void main(String[] args) {
        int[][] grid = readGrid(new Scanner(System.in));
        solve(grid);
        System.out.println("The total number of solutions is " + solutionCount);
        for (int i = 0; i < samples.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            printGrid(samples.get(i));
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

    private static void solve(int[][] grid) {
        int row = -1;
        int column = -1;
        boolean complete = true;
        for (int i = 0; i < 9 && complete; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    column = j;
                    complete = false;
                    break;
                }
            }
        }
        if (complete) {
            solutionCount++;
            if (samples.size() < 2) {
                samples.add(copy(grid));
            }
            return;
        }

        for (int value = 1; value <= 9; value++) {
            if (isValid(grid, row, column, value)) {
                grid[row][column] = value;
                solve(grid);
                grid[row][column] = 0;
            }
        }
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

    private static int[][] copy(int[][] grid) {
        int[][] result = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grid[i], 0, result[i], 0, 9);
        }
        return result;
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
