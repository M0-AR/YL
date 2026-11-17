package Exercise_19._16;

import java.util.ArrayList;
import java.util.List;

public class Exercise_19_16 {
  public static class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
      this.first = first;
      this.second = second;
    }

    public A getFirst() {
      return first;
    }

    public B getSecond() {
      return second;
    }
  }

  public static <A, B> List<Pair<A, B>> zip(List<A> first, List<B> second) {
    int size = Math.min(first.size(), second.size());
    List<Pair<A, B>> result = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      result.add(new Pair<>(first.get(i), second.get(i)));
    }
    return result;
  }

  public static void main(String[] args) {
    List<Pair<String, Integer>> pairs = zip(
        List.of("a", "b", "c"), List.of(1, 2, 3, 4));
    check(pairs.size() == 3);
    check(pairs.get(1).getFirst().equals("b"));
    check(pairs.get(2).getSecond() == 3);
    System.out.println("Exercise_19_16 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
