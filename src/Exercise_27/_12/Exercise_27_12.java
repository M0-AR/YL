package Exercise_27._12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise_27_12 {
  public static <E> List<E> setToList(Set<E> set) {
    return new ArrayList<>(set);
  }

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>(List.of(3, 1, 2));
    List<Integer> list = setToList(set);
    check(list.size() == 3);
    check(list.containsAll(set));
    System.out.println("Exercise_27_12 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
