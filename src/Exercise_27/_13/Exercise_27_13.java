package Exercise_27._13;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise_27_13 {
  public static <E> Set<E> listToSet(List<E> list) {
    return new HashSet<>(list);
  }

  public static void main(String[] args) {
    Set<Integer> set = listToSet(List.of(1, 2, 2, 3, 1));
    check(set.size() == 3);
    check(set.contains(1) && set.contains(2) && set.contains(3));
    System.out.println("Exercise_27_13 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
