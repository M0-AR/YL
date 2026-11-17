package Exercise_19._14;

public class Exercise_19_14 {
  public static class MinMax<E extends Comparable<E>> {
    private final E min;
    private final E max;

    public MinMax(E min, E max) {
      this.min = min;
      this.max = max;
    }

    public E getMin() {
      return min;
    }

    public E getMax() {
      return max;
    }
  }

  public static <E extends Comparable<E>> MinMax<E> minMax(E[] list) {
    if (list.length == 0) {
      throw new IllegalArgumentException("empty list");
    }
    E min = list[0];
    E max = list[0];
    for (E value : list) {
      if (value.compareTo(min) < 0) {
        min = value;
      }
      if (value.compareTo(max) > 0) {
        max = value;
      }
    }
    return new MinMax<>(min, max);
  }

  public static void main(String[] args) {
    MinMax<Integer> numbers = minMax(new Integer[]{4, 1, 9, 3});
    check(numbers.getMin() == 1 && numbers.getMax() == 9);
    MinMax<String> words = minMax(new String[]{"cat", "ant", "dog"});
    check(words.getMin().equals("ant") && words.getMax().equals("dog"));
    System.out.println("Exercise_19_14 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
