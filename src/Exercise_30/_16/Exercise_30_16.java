package Exercise_30._16;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise_30_16 {
    private static final int SIZE = 2000;

    public static void main(String[] args) {
        double[][] a = randomMatrix();
        double[][] b = randomMatrix();

        long start = System.nanoTime();
        double[][] parallel = parallelAddMatrix(a, b);
        long parallelTime = System.nanoTime() - start;

        start = System.nanoTime();
        double[][] sequential = new double[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                sequential[row][column] = a[row][column] + b[row][column];
            }
        }
        long sequentialTime = System.nanoTime() - start;

        System.out.printf("Parallel time:   %d ms%n", parallelTime / 1_000_000);
        System.out.printf("Sequential time: %d ms%n", sequentialTime / 1_000_000);
        System.out.println("Results match: " + java.util.Arrays.deepEquals(parallel, sequential));
    }

    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new AddTask(a, b, result, 0, a.length));
        return result;
    }

    private static double[][] randomMatrix() {
        Random random = new Random(3);
        double[][] matrix = new double[SIZE][SIZE];
        for (double[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = random.nextDouble();
            }
        }
        return matrix;
    }

    private static class AddTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 100;
        private final double[][] a;
        private final double[][] b;
        private final double[][] result;
        private final int low;
        private final int high;

        AddTask(double[][] a, double[][] b, double[][] result, int low, int high) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                for (int row = low; row < high; row++) {
                    for (int column = 0; column < a[row].length; column++) {
                        result[row][column] = a[row][column] + b[row][column];
                    }
                }
            } else {
                int middle = (low + high) / 2;
                invokeAll(new AddTask(a, b, result, low, middle),
                        new AddTask(a, b, result, middle, high));
            }
        }
    }
}
