package Exercise_27._17;

import java.util.Objects;

public class Exercise_27_17 {
  public static class LinearProbingHashTable<K, V> {
    private final Object[] keys;
    private final Object[] values;
    private int size;

    public LinearProbingHashTable(int capacity) {
      keys = new Object[capacity];
      values = new Object[capacity];
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      int index = Math.floorMod(key.hashCode(), keys.length);
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i) % keys.length;
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
        int slot = (index + i) % keys.length;
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
    LinearProbingHashTable<String, Integer> table = new LinearProbingHashTable<>(17);
    table.put("alpha", 1);
    table.put("beta", 2);
    table.put("gamma", 3);
    check(table.get("alpha") == 1);
    check(table.get("beta") == 2);
    check(table.get("gamma") == 3);
    check(table.get("delta") == null);
    check(table.size() == 3);
    System.out.println("Exercise_27_17 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
