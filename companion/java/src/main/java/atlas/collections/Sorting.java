package atlas.collections;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class Sorting {
    private Sorting() {
    }

    public static void insertionSort(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int key = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > key) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = key;
        }
    }

    public static void mergeSort(int[] values) {
        if (values.length < 2) return;
        int mid = values.length / 2;
        int[] left = Arrays.copyOfRange(values, 0, mid);
        int[] right = Arrays.copyOfRange(values, mid, values.length);
        mergeSort(left);
        mergeSort(right);
        merge(values, left, right);
    }

    private static void merge(int[] output, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            output[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) output[k++] = left[i++];
        while (j < right.length) output[k++] = right[j++];
    }

    public static void quickSort(int[] values) {
        quickSort(values, 0, values.length - 1);
    }

    private static void quickSort(int[] values, int low, int high) {
        if (low >= high) return;
        int pivot = ThreadLocalRandom.current().nextInt(low, high + 1);
        swap(values, pivot, high);
        int split = partition(values, low, high);
        quickSort(values, low, split - 1);
        quickSort(values, split + 1, high);
    }

    private static int partition(int[] values, int low, int high) {
        int pivot = values[high];
        int boundary = low;
        for (int j = low; j < high; j++) {
            if (values[j] <= pivot) {
                swap(values, boundary, j);
                boundary++;
            }
        }
        swap(values, boundary, high);
        return boundary;
    }

    private static void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }

    public static boolean isSorted(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] values = {9, 4, 7, 1, 8, 2, 6, 3, 5};
        quickSort(values);
        System.out.println(Arrays.toString(values));
    }
}
