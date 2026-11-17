package Exercise_27._25;

import java.util.HashSet;
import java.util.Set;

public class Exercise_27_25 {
  public static <E> Set<E> intersection(Set<E> first, Set<E> second) {
    Set<E> result = new HashSet<>(first);
    result.retainAll(second);
    return result;
  }

  public static void main(String[] args) {
    Set<Integer> result = intersection(Set.of(1, 2, 3, 4), Set.of(3, 4, 5));
    check(result.equals(Set.of(3, 4)));
    System.out.println("Exercise_27_25 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
