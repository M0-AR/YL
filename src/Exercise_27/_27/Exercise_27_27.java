package Exercise_27._27;

import java.util.HashSet;
import java.util.Set;

public class Exercise_27_27 {
  public static <E> Set<E> difference(Set<E> first, Set<E> second) {
    Set<E> result = new HashSet<>(first);
    result.removeAll(second);
    return result;
  }

  public static void main(String[] args) {
    Set<Integer> result = difference(Set.of(1, 2, 3, 4), Set.of(3, 4, 5));
    check(result.equals(Set.of(1, 2)));
    System.out.println("Exercise_27_27 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
