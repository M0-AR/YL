package Exercise_30._17;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise_30_17 {
    private static final int SIZE = 400;

    public static void main(String[] args) {
        double[][] a = randomMatrix();
        double[][] b = randomMatrix();

        long start = System.nanoTime();
        double[][] parallel = parallelMultiplyMatrix(a, b);
        long parallelTime = System.nanoTime() - start;

        start = System.nanoTime();
        double[][] sequential = multiplySequentially(a, b);
        long sequentialTime = System.nanoTime() - start;

        System.out.println("Test size: " + SIZE + " (the method supports 2000)");
        System.out.printf("Parallel time:   %d ms%n", parallelTime / 1_000_000);
        System.out.printf("Sequential time: %d ms%n", sequentialTime / 1_000_000);
        System.out.println("Results match: " + java.util.Arrays.deepEquals(parallel, sequential));
    }

    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MultiplyTask(a, b, result, 0, a.length));
        return result;
    }

    private static double[][] multiplySequentially(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        multiplyRows(a, b, result, 0, a.length);
        return result;
    }

    private static void multiplyRows(double[][] a, double[][] b, double[][] result,
                                     int low, int high) {
        int columns = b[0].length;
        for (int row = low; row < high; row++) {
            for (int k = 0; k < b.length; k++) {
                double value = a[row][k];
                for (int column = 0; column < columns; column++) {
                    result[row][column] += value * b[k][column];
                }
            }
        }
    }

    private static double[][] randomMatrix() {
        Random random = new Random(9);
        double[][] matrix = new double[SIZE][SIZE];
        for (double[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = random.nextDouble();
            }
        }
        return matrix;
    }

    private static class MultiplyTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 50;
        private final double[][] a;
        private final double[][] b;
        private final double[][] result;
        private final int low;
        private final int high;

        MultiplyTask(double[][] a, double[][] b, double[][] result, int low, int high) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                multiplyRows(a, b, result, low, high);
            } else {
                int middle = (low + high) / 2;
                invokeAll(new MultiplyTask(a, b, result, low, middle),
                        new MultiplyTask(a, b, result, middle, high));
            }
        }
    }
}
