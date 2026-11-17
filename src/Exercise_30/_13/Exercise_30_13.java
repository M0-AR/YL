package Exercise_30._13;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise_30_13 {
    public static void main(String[] args) {
        Integer[] list = new Integer[1_000_000];
        java.util.Random random = new java.util.Random(7);
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextInt();
        }
        parallelMergeSort(list);
        System.out.println("Sorted: " + isSorted(list));
        System.out.println("First: " + list[0] + ", last: " + list[list.length - 1]);
    }

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SortTask<>(list, 0, list.length));
    }

    private static <E extends Comparable<E>> boolean isSorted(E[] list) {
        for (int i = 1; i < list.length; i++) {
            if (list[i - 1].compareTo(list[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private static final int THRESHOLD = 50_000;
        private final E[] list;
        private final int low;
        private final int high;

        SortTask(E[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        @SuppressWarnings({"unchecked", "rawtypes"})
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrays.sort(list, low, high);
                return;
            }
            int middle = (low + high) / 2;
            invokeAll(new SortTask<>(list, low, middle),
                    new SortTask<>(list, middle, high));
            E[] temp = (E[]) new Comparable[high - low];
            int i = low;
            int j = middle;
            int k = 0;
            while (i < middle && j < high) {
                if (list[i].compareTo(list[j]) <= 0) {
                    temp[k++] = list[i++];
                } else {
                    temp[k++] = list[j++];
                }
            }
            while (i < middle) {
                temp[k++] = list[i++];
            }
            while (j < high) {
                temp[k++] = list[j++];
            }
            System.arraycopy(temp, 0, list, low, temp.length);
        }
    }
}
