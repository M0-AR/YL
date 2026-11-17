package Exercise_19._20;

import java.util.function.Function;

public class Exercise_19_20 {
  public static class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }

  public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
    return new Pair<>(pair.getValue(), pair.getKey());
  }

  public static <K, V, R> Pair<K, R> mapValue(
      Pair<K, V> pair, Function<? super V, ? extends R> mapper) {
    return new Pair<>(pair.getKey(), mapper.apply(pair.getValue()));
  }

  public static void main(String[] args) {
    Pair<String, Integer> pair = new Pair<>("age", 42);
    Pair<Integer, String> swapped = swap(pair);
    check(swapped.getKey() == 42);
    check(swapped.getValue().equals("age"));
    Pair<String, String> mapped = mapValue(pair, value -> "v" + value);
    check(mapped.getValue().equals("v42"));
    System.out.println("Exercise_19_20 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
