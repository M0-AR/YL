package Exercise_30._12;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise_30_12 {
    private static final int SIZE = 9_000_000;

    public static void main(String[] args) {
        double[] parallel = new double[SIZE];
        long start = System.nanoTime();
        parallelAssignValues(parallel);
        long parallelTime = System.nanoTime() - start;

        double[] sequential = new double[SIZE];
        start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < sequential.length; i++) {
            sequential[i] = random.nextDouble();
        }
        long sequentialTime = System.nanoTime() - start;

        System.out.printf("Parallel time:   %d ms%n", parallelTime / 1_000_000);
        System.out.printf("Sequential time: %d ms%n", sequentialTime / 1_000_000);
        System.out.println("All values in [0, 1): " + allInRange(parallel));
    }

    public static void parallelAssignValues(double[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new AssignTask(list, 0, list.length));
    }

    private static boolean allInRange(double[] list) {
        for (double value : list) {
            if (value < 0 || value >= 1) {
                return false;
            }
        }
        return true;
    }

    private static class AssignTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 50_000;
        private final double[] list;
        private final int low;
        private final int high;

        AssignTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Random random = new Random();
                for (int i = low; i < high; i++) {
                    list[i] = random.nextDouble();
                }
            } else {
                int middle = (low + high) / 2;
                invokeAll(new AssignTask(list, low, middle),
                        new AssignTask(list, middle, high));
            }
        }
    }
}
