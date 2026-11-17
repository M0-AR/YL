package Exercise_20._28;

import java.util.ArrayList;
import java.util.List;

public class Exercise_20_28 {
  public static <E> void reverse(List<E> list) {
    for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
      E temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
    }
  }

  public static void main(String[] args) {
    List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    reverse(values);
    check(values.equals(List.of(5, 4, 3, 2, 1)));
    System.out.println("Exercise_20_28 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
