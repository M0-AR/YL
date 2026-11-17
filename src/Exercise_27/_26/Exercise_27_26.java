package Exercise_27._26;

import java.util.HashSet;
import java.util.Set;

public class Exercise_27_26 {
  public static <E> Set<E> union(Set<E> first, Set<E> second) {
    Set<E> result = new HashSet<>(first);
    result.addAll(second);
    return result;
  }

  public static void main(String[] args) {
    Set<Integer> result = union(Set.of(1, 2, 3), Set.of(3, 4, 5));
    check(result.equals(Set.of(1, 2, 3, 4, 5)));
    System.out.println("Exercise_27_26 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
