package Exercise_23;

import java.util.Arrays;

public class Chapter23 {
    public static int[] exerciseOne(int[] numbers) {
        int[] sorted = copy(numbers);
        for (int i = 1; i < sorted.length; i++) {
            int key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j] > key) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    public static int[] exerciseTwo(int[] numbers) {
        int[] sorted = copy(numbers);
        mergeSort(sorted, 0, sorted.length - 1);
        return sorted;
    }

    public static int[] exerciseThree(int[] numbers) {
        int[] sorted = copy(numbers);
        quickSort(sorted, 0, sorted.length - 1);
        return sorted;
    }

    private static int[] copy(int[] numbers) {
        return numbers == null ? new int[0] : Arrays.copyOf(numbers, numbers.length);
    }

    private static void mergeSort(int[] data, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        mergeSort(data, low, middle);
        mergeSort(data, middle + 1, high);
        merge(data, low, middle, high);
    }

    private static void merge(int[] data, int low, int middle, int high) {
        int[] buffer = new int[high - low + 1];
        int left = low;
        int right = middle + 1;
        int index = 0;
        while (left <= middle && right <= high) {
            if (data[left] <= data[right]) {
                buffer[index++] = data[left++];
            } else {
                buffer[index++] = data[right++];
            }
        }
        while (left <= middle) {
            buffer[index++] = data[left++];
        }
        while (right <= high) {
            buffer[index++] = data[right++];
        }
        System.arraycopy(buffer, 0, data, low, buffer.length);
    }

    private static void quickSort(int[] data, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = data[high];
        int smaller = low - 1;
        for (int i = low; i < high; i++) {
            if (data[i] <= pivot) {
                smaller++;
                swap(data, smaller, i);
            }
        }
        swap(data, smaller + 1, high);
        int split = smaller + 1;
        quickSort(data, low, split - 1);
        quickSort(data, split + 1, high);
    }

    private static void swap(int[] data, int first, int second) {
        int temporary = data[first];
        data[first] = data[second];
        data[second] = temporary;
    }

    public static void main(String[] args) {
        int[] source = {9, 3, 7, 1, 8, 2, 7};
        System.out.println(Arrays.toString(exerciseOne(source)));
        System.out.println(Arrays.toString(exerciseTwo(source)));
        System.out.println(Arrays.toString(exerciseThree(source)));
    }
}
