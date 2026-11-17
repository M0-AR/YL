package Exercise_30._15;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Exercise_30_15 {
    private static final int SIZE = 9_000_000;

    public static void main(String[] args) {
        double[] list = new double[SIZE];
        Random random = new Random(5);
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextDouble();
        }

        long start = System.nanoTime();
        double parallel = parallelSum(list);
        long parallelTime = System.nanoTime() - start;

        start = System.nanoTime();
        double sequential = 0;
        for (double value : list) {
            sequential += value;
        }
        long sequentialTime = System.nanoTime() - start;

        System.out.printf("Parallel sum:   %.6f in %d ms%n", parallel, parallelTime / 1_000_000);
        System.out.printf("Sequential sum: %.6f in %d ms%n", sequential, sequentialTime / 1_000_000);
        System.out.println("Sums match within 1e-6: " + (Math.abs(parallel - sequential) < 1e-6));
    }

    public static double parallelSum(double[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new SumTask(list, 0, list.length));
    }

    private static class SumTask extends RecursiveTask<Double> {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 50_000;
        private final double[] list;
        private final int low;
        private final int high;

        SumTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Double compute() {
            if (high - low <= THRESHOLD) {
                double sum = 0;
                for (int i = low; i < high; i++) {
                    sum += list[i];
                }
                return sum;
            }
            int middle = (low + high) / 2;
            SumTask left = new SumTask(list, low, middle);
            SumTask right = new SumTask(list, middle, high);
            left.fork();
            double rightResult = right.compute();
            return left.join() + rightResult;
        }
    }
}
