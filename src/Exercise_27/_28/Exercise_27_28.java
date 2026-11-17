package Exercise_27._28;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise_27_28 {
  public static <E> E firstDuplicate(List<E> values) {
    Set<E> seen = new HashSet<>();
    for (E value : values) {
      if (!seen.add(value)) {
        return value;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    check(firstDuplicate(List.of(2, 1, 3, 2, 1)) == 2);
    check(firstDuplicate(List.of(1, 2, 3)) == null);
    System.out.println("Exercise_27_28 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
