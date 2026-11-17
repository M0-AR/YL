package Exercise_20._24;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Exercise_20_24 {
  public static <E> List<E> removeDuplicates(List<E> list) {
    return new ArrayList<>(new LinkedHashSet<>(list));
  }

  public static void main(String[] args) {
    List<Integer> result = removeDuplicates(List.of(3, 1, 3, 2, 1, 4));
    check(result.equals(List.of(3, 1, 2, 4)));
    System.out.println("Exercise_20_24 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
