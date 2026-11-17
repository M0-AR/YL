package Exercise_27._18;

import java.util.Objects;

public class Exercise_27_18 {
  public static class QuadraticProbingHashTable<K, V> {
    private final Object[] keys;
    private final Object[] values;
    private int size;

    public QuadraticProbingHashTable(int capacity) {
      keys = new Object[capacity];
      values = new Object[capacity];
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      int index = Math.floorMod(key.hashCode(), keys.length);
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i * i) % keys.length;
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
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i * i) % keys.length;
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
    QuadraticProbingHashTable<String, Integer> table = new QuadraticProbingHashTable<>(19);
    table.put("red", 1);
    table.put("green", 2);
    table.put("blue", 3);
    check(table.get("red") == 1);
    check(table.get("green") == 2);
    check(table.get("blue") == 3);
    check(table.get("black") == null);
    check(table.size() == 3);
    System.out.println("Exercise_27_18 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
