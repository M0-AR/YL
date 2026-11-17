package Exercise_19._15;

import java.util.function.Predicate;

public class Exercise_19_15 {
  public static <E> int count(E[] list, Predicate<? super E> filter) {
    int count = 0;
    for (E value : list) {
      if (filter.test(value)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Integer[] numbers = {1, 2, 3, 4, 5, 6};
    check(count(numbers, value -> value % 2 == 0) == 3);
    String[] words = {"java", "is", "fun", "and", "useful"};
    check(count(words, value -> value.length() > 3) == 2);
    System.out.println("Exercise_19_15 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
