package Exercise_23._12;

import java.util.Random;

public class Exercise_23_12 {
    private static final int COUNT = 1_000_000;

    public static void main(String[] args) {
        Random random = new Random(23);
        int[] values = new int[COUNT];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(10_000_000);
        }
        long start = System.currentTimeMillis();
        radixSort(values);
        System.out.println("Radix sort of " + COUNT + " integers took "
                + (System.currentTimeMillis() - start) + " ms");
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
}
