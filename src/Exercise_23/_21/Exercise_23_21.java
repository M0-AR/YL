package Exercise_23._21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exercise_23_21 {
  public static void bucketSort(int[] values, int maxValue) {
    List<List<Integer>> buckets = new ArrayList<>();
    for (int i = 0; i <= maxValue; i++) {
      buckets.add(new ArrayList<>());
    }
    for (int value : values) {
      if (value < 0 || value > maxValue) {
        throw new IllegalArgumentException("value out of range");
      }
      buckets.get(value).add(value);
    }
    int index = 0;
    for (List<Integer> bucket : buckets) {
      for (int value : bucket) {
        values[index++] = value;
      }
    }
  }

  public static void main(String[] args) {
    Random random = new Random(11);
    int[] values = new int[40];
    for (int i = 0; i < values.length; i++) {
      values[i] = random.nextInt(100);
    }
    int[] expected = values.clone();
    Arrays.sort(expected);
    bucketSort(values, 99);
    check(Arrays.equals(values, expected));
    System.out.println("Exercise_23_21 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
