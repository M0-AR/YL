package Exercise_20._35;

import java.util.LinkedHashMap;
import java.util.Map;

public class Exercise_20_35 {
  public static class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
      super(capacity, 0.75f, true);
      this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
      return size() > capacity;
    }
  }

  public static void main(String[] args) {
    LRUCache<Integer, String> cache = new LRUCache<>(2);
    cache.put(1, "one");
    cache.put(2, "two");
    cache.get(1);
    cache.put(3, "three");
    check(!cache.containsKey(2));
    check(cache.get(1).equals("one"));
    check(cache.get(3).equals("three"));
    System.out.println("Exercise_20_35 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
