package Exercise_23._24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise_23_24 {
  public static void main(String[] args) {
    List<String> words = new ArrayList<>(List.of(
        "pear", "fig", "apple", "kiwi", "date", "plum"));
    words.sort(Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder()));
    check(words.equals(List.of("fig", "date", "kiwi", "pear", "plum", "apple")));
    System.out.println("Exercise_23_24 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
