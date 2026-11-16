package Exercise_07._16;

import java.util.Arrays;

public class ExecutionTime {
    public static void main(String[] args) {
        final int length = 100000;
        int[] numbers = new int[length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * length);
        }

        int key = numbers[(int) (Math.random() * numbers.length)];

        long start = System.nanoTime();
        int linearIndex = linearSearch(numbers, key);
        long linearTime = System.nanoTime() - start;

        Arrays.sort(numbers);

        start = System.nanoTime();
        int binaryIndex = binarySearch(numbers, key);
        long binaryTime = System.nanoTime() - start;

        System.out.printf("Linear search: %d nanoseconds (index %d)%n", linearTime, linearIndex);
        System.out.printf("Binary search: %d nanoseconds (index %d)%n", binaryTime, binaryIndex);
    }

    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list[mid]) {
                high = mid - 1;
            } else if (key > list[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -low - 1;
    }
}
