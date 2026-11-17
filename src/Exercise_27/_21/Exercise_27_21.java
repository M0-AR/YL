package Exercise_27._21;

import java.util.ArrayList;
import java.util.List;

public class Exercise_27_21 {
  private static class Entry {
    private final String key;
    private final int value;

    private Entry(String key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class CollisionCounter {
    private final List<Entry>[] buckets;
    private int collisions;

    @SuppressWarnings("unchecked")
    public CollisionCounter(int capacity) {
      buckets = (List<Entry>[]) new List<?>[capacity];
      for (int i = 0; i < buckets.length; i++) {
        buckets[i] = new ArrayList<>();
      }
    }

    public void put(String key, int value) {
      List<Entry> bucket = buckets[Math.floorMod(key.hashCode(), buckets.length)];
      for (Entry entry : bucket) {
        if (entry.key.equals(key)) {
          return;
        }
      }
      if (!bucket.isEmpty()) {
        collisions++;
      }
      bucket.add(new Entry(key, value));
    }

    public int collisions() {
      return collisions;
    }
  }

  public static void main(String[] args) {
    CollisionCounter table = new CollisionCounter(16);
    table.put("Aa", 1);
    table.put("BB", 2);
    table.put("C", 3);
    check(table.collisions() >= 1);
    System.out.println("Exercise_27_21 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
