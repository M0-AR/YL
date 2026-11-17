package Exercise_27._19;

import java.util.Objects;

public class Exercise_27_19 {
  public static class DoubleHashingHashTable<K, V> {
    private final Object[] keys;
    private final Object[] values;
    private int size;

    public DoubleHashingHashTable(int capacity) {
      keys = new Object[capacity];
      values = new Object[capacity];
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      int index = Math.floorMod(key.hashCode(), keys.length);
      int step = 1 + Math.floorMod(key.hashCode(), keys.length - 1);
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i * step) % keys.length;
        if (keys[slot] == null) {
          keys[slot] = key;
          values[slot] = value;
          size++;
          return;
        }
        if (keys[slot].equals(key)) {
          values[slot] = value;
          return;
        }
      }
      throw new IllegalStateException("table full");
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
      int index = Math.floorMod(key.hashCode(), keys.length);
      int step = 1 + Math.floorMod(key.hashCode(), keys.length - 1);
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i * step) % keys.length;
        if (keys[slot] == null) {
          return null;
        }
        if (keys[slot].equals(key)) {
          return (V) values[slot];
        }
      }
      return null;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    DoubleHashingHashTable<String, Integer> table = new DoubleHashingHashTable<>(17);
    table.put("cat", 1);
    table.put("dog", 2);
    table.put("bird", 3);
    check(table.get("cat") == 1);
    check(table.get("dog") == 2);
    check(table.get("bird") == 3);
    check(table.get("fish") == null);
    check(table.size() == 3);
    System.out.println("Exercise_27_19 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
