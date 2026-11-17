package Exercise_23._23;

import java.util.Arrays;
import java.util.Random;

public class Exercise_23_23 {
  public static void countingSort(int[] values, int maxValue) {
    int[] counts = new int[maxValue + 1];
    for (int value : values) {
      if (value < 0 || value > maxValue) {
        throw new IllegalArgumentException("value out of range");
      }
      counts[value]++;
    }
    for (int i = 1; i < counts.length; i++) {
      counts[i] += counts[i - 1];
    }
    int[] output = new int[values.length];
    for (int i = values.length - 1; i >= 0; i--) {
      output[--counts[values[i]]] = values[i];
    }
    System.arraycopy(output, 0, values, 0, values.length);
  }

  public static void main(String[] args) {
    Random random = new Random(17);
    int[] values = new int[30];
    for (int i = 0; i < values.length; i++) {
      values[i] = random.nextInt(50);
    }
    int[] expected = values.clone();
    Arrays.sort(expected);
    countingSort(values, 49);
    check(Arrays.equals(values, expected));
    System.out.println("Exercise_23_23 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
