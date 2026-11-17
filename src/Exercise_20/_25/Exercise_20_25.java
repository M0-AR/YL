package Exercise_20._25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Exercise_20_25 {
  public static <E> void shuffle(List<E> list, Random random) {
    for (int i = list.size() - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      Collections.swap(list, i, j);
    }
  }

  public static void main(String[] args) {
    List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
    List<Integer> expected = new ArrayList<>(values);
    shuffle(values, new Random(7));
    Collections.sort(expected);
    List<Integer> actual = new ArrayList<>(values);
    Collections.sort(actual);
    check(actual.equals(expected));
    System.out.println("Exercise_20_25 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
