package Exercise_30._18;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Exercise_30_18 {
    public static void main(String[] args) {
        int[] columns = new int[8];
        java.util.Arrays.fill(columns, -1);
        ForkJoinPool pool = new ForkJoinPool();
        int count = pool.invoke(new QueensTask(0, columns, 0, 8));
        System.out.println("Number of solutions: " + count);
    }

    private static class QueensTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;
        private final int row;
        private final int[] columns;
        private final int lowColumn;
        private final int highColumn;

        QueensTask(int row, int[] columns, int lowColumn, int highColumn) {
            this.row = row;
            this.columns = columns;
            this.lowColumn = lowColumn;
            this.highColumn = highColumn;
        }

        @Override
        protected Integer compute() {
            if (row == 8) {
                return 1;
            }
            if (row == 0 && highColumn - lowColumn > 1) {
                QueensTask[] tasks = new QueensTask[highColumn - lowColumn];
                for (int column = lowColumn; column < highColumn; column++) {
                    tasks[column - lowColumn] = new QueensTask(0, new int[8], column, column + 1);
                    tasks[column - lowColumn].fork();
                }
                int total = 0;
                for (QueensTask task : tasks) {
                    total += task.join();
                }
                return total;
            }
            int total = 0;
            for (int column = lowColumn; column < highColumn; column++) {
                if (isValid(columns, row, column)) {
                    columns[row] = column;
                    total += computeRemaining(row + 1, columns);
                    columns[row] = -1;
                }
            }
            return total;
        }

        private int computeRemaining(int nextRow, int[] board) {
            if (nextRow == 8) {
                return 1;
            }
            int total = 0;
            for (int column = 0; column < 8; column++) {
                if (isValid(board, nextRow, column)) {
                    board[nextRow] = column;
                    total += computeRemaining(nextRow + 1, board);
                    board[nextRow] = -1;
                }
            }
            return total;
        }
    }

    private static boolean isValid(int[] columns, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (columns[i] == column || Math.abs(columns[i] - column) == row - i) {
                return false;
            }
        }
        return true;
    }
}
