package Exercise_23._13;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class Exercise_23_13 {
    private static final int[] SIZES = {
        50_000, 100_000, 150_000, 200_000, 250_000, 300_000
    };

    public static void main(String[] args) {
        System.out.printf("%-10s%14s%14s%14s%14s%14s%14s%n", "Array", "Selection",
                "Bubble", "Merge", "Quick", "Heap", "Radix");
        for (int size : SIZES) {
            long[] times = new long[6];
            times[0] = time(size, Exercise_23_13::selectionSort);
            times[1] = time(size, Exercise_23_13::bubbleSort);
            times[2] = time(size, Exercise_23_13::mergeSort);
            times[3] = time(size, Exercise_23_13::quickSort);
            times[4] = time(size, Exercise_23_13::heapSort);
            times[5] = time(size, Exercise_23_13::radixSort);
            System.out.printf("%,-10d%,14d%,14d%,14d%,14d%,14d%,14d%n",
                    size, times[0], times[1], times[2], times[3], times[4], times[5]);
        }
    }

    private static long time(int size, java.util.function.Consumer<int[]> sort) {
        Random random = new Random(23);
        int[] values = new int[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(Integer.MAX_VALUE);
        }
        long start = System.currentTimeMillis();
        sort.accept(values);
        return System.currentTimeMillis() - start;
    }

    public static void selectionSort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[smallest]) {
                    smallest = j;
                }
            }
            swap(values, i, smallest);
        }
    }

    public static void bubbleSort(int[] values) {
        for (int pass = 0; pass < values.length - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < values.length - 1 - pass; i++) {
                if (values[i] > values[i + 1]) {
                    swap(values, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    public static void mergeSort(int[] values) {
        if (values.length < 2) {
            return;
        }
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(int[] values, int[] temp, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        mergeSort(values, temp, low, middle);
        mergeSort(values, temp, middle + 1, high);
        int left = low;
        int right = middle + 1;
        int index = low;
        while (left <= middle && right <= high) {
            temp[index++] = values[left] <= values[right] ? values[left++] : values[right++];
        }
        while (left <= middle) {
            temp[index++] = values[left++];
        }
        while (right <= high) {
            temp[index++] = values[right++];
        }
        System.arraycopy(temp, low, values, low, high - low + 1);
    }

    public static void quickSort(int[] values) {
        Deque<int[]> ranges = new ArrayDeque<>();
        ranges.push(new int[] {0, values.length - 1});
        while (!ranges.isEmpty()) {
            int[] range = ranges.pop();
            int low = range[0];
            int high = range[1];
            while (low < high) {
                int pivot = partition(values, low, high);
                if (pivot - low < high - pivot) {
                    if (pivot + 1 < high) {
                        ranges.push(new int[] {pivot + 1, high});
                    }
                    high = pivot - 1;
                } else {
                    if (low < pivot - 1) {
                        ranges.push(new int[] {low, pivot - 1});
                    }
                    low = pivot + 1;
                }
            }
        }
    }

    private static int partition(int[] values, int low, int high) {
        int pivot = values[high];
        int boundary = low;
        for (int i = low; i < high; i++) {
            if (values[i] < pivot) {
                swap(values, i, boundary++);
            }
        }
        swap(values, boundary, high);
        return boundary;
    }

    public static void heapSort(int[] values) {
        int size = values.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(values, i, size);
        }
        for (int end = size - 1; end > 0; end--) {
            swap(values, 0, end);
            siftDown(values, 0, end);
        }
    }

    private static void siftDown(int[] values, int index, int size) {
        while (true) {
            int left = index * 2 + 1;
            int right = left + 1;
            int largest = index;
            if (left < size && values[left] > values[largest]) {
                largest = left;
            }
            if (right < size && values[right] > values[largest]) {
                largest = right;
            }
            if (largest == index) {
                return;
            }
            swap(values, index, largest);
            index = largest;
        }
    }

    public static void radixSort(int[] values) {
        if (values.length < 2) {
            return;
        }
        int maximum = 0;
        for (int value : values) {
            maximum = Math.max(maximum, value);
        }
        int[] buffer = new int[values.length];
        for (long place = 1; maximum / place > 0; place *= 10) {
            int[] counts = new int[10];
            for (int value : values) {
                counts[(int) (value / place % 10)]++;
            }
            for (int i = 1; i < 10; i++) {
                counts[i] += counts[i - 1];
            }
            for (int i = values.length - 1; i >= 0; i--) {
                int digit = (int) (values[i] / place % 10);
                buffer[--counts[digit]] = values[i];
            }
            System.arraycopy(buffer, 0, values, 0, values.length);
        }
    }

    private static void swap(int[] values, int first, int second) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }
}
