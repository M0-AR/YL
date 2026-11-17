package Exercise_20._27;

import java.util.ArrayList;
import java.util.List;

public class Exercise_20_27 {
  public static <E extends Comparable<E>> List<E> merge(List<E> first, List<E> second) {
    List<E> result = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < first.size() && j < second.size()) {
      if (first.get(i).compareTo(second.get(j)) <= 0) {
        result.add(first.get(i++));
      } else {
        result.add(second.get(j++));
      }
    }
    while (i < first.size()) {
      result.add(first.get(i++));
    }
    while (j < second.size()) {
      result.add(second.get(j++));
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = merge(List.of(1, 4, 9), List.of(2, 3, 10, 11));
    check(result.equals(List.of(1, 2, 3, 4, 9, 10, 11)));
    System.out.println("Exercise_20_27 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
