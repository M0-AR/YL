package Exercise_19._12;

import java.util.Arrays;

public class Exercise_19_12 {
  public static <E> void swap(E[] list, int i, int j) {
    E temp = list[i];
    list[i] = list[j];
    list[j] = temp;
  }

  public static void main(String[] args) {
    Integer[] numbers = {1, 2, 3, 4};
    swap(numbers, 0, 3);
    check(Arrays.equals(numbers, new Integer[]{4, 2, 3, 1}));
    String[] words = {"a", "b", "c"};
    swap(words, 1, 2);
    check(Arrays.equals(words, new String[]{"a", "c", "b"}));
    System.out.println("Exercise_19_12 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
