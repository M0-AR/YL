package Exercise_30._14;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise_30_14 {
    private static final int SIZE = 9_000_000;

    public static void main(String[] args) {
        int[] parallel = randomArray();
        long start = System.nanoTime();
        parallelQuickSort(parallel);
        long parallelTime = System.nanoTime() - start;

        int[] sequential = randomArray();
        start = System.nanoTime();
        quickSort(sequential, 0, sequential.length - 1);
        long sequentialTime = System.nanoTime() - start;

        System.out.printf("Parallel time:   %d ms%n", parallelTime / 1_000_000);
        System.out.printf("Sequential time: %d ms%n", sequentialTime / 1_000_000);
        System.out.println("Parallel sorted: " + isSorted(parallel));
        System.out.println("Sequential sorted: " + isSorted(sequential));
    }

    public static void parallelQuickSort(int[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SortTask(list, 0, list.length - 1));
    }

    public static void quickSort(int[] list, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(list, low, high);
        quickSort(list, low, pivot - 1);
        quickSort(list, pivot + 1, high);
    }

    private static int partition(int[] list, int low, int high) {
        int middle = (low + high) >>> 1;
        if (list[middle] < list[low]) {
            swap(list, middle, low);
        }
        if (list[high] < list[low]) {
            swap(list, high, low);
        }
        if (list[high] < list[middle]) {
            swap(list, high, middle);
        }
        swap(list, middle, high);
        int pivot = list[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list[j] <= pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    private static int[] randomArray() {
        int[] list = new int[SIZE];
        Random random = new Random(11);
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextInt();
        }
        return list;
    }

    private static boolean isSorted(int[] list) {
        for (int i = 1; i < list.length; i++) {
            if (list[i - 1] > list[i]) {
                return false;
            }
        }
        return true;
    }

    private static class SortTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 50_000;
        private final int[] list;
        private final int low;
        private final int high;

        SortTask(int[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrays.sort(list, low, high + 1);
                return;
            }
            int pivot = partition(list, low, high);
            invokeAll(new SortTask(list, low, pivot - 1),
                    new SortTask(list, pivot + 1, high));
        }
    }
}
