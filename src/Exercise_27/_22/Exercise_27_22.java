package Exercise_27._22;

import java.util.Objects;

public class Exercise_27_22 {
  public static class HashTableWithRemove<K, V> {
    private final Object[] keys;
    private final Object[] values;
    private final boolean[] deleted;
    private int size;

    public HashTableWithRemove(int capacity) {
      keys = new Object[capacity];
      values = new Object[capacity];
      deleted = new boolean[capacity];
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      int index = Math.floorMod(key.hashCode(), keys.length);
      int firstDeleted = -1;
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i) % keys.length;
        if (keys[slot] == null) {
          int target = firstDeleted >= 0 ? firstDeleted : slot;
          keys[target] = key;
          values[target] = value;
          deleted[target] = false;
          size++;
          return;
        }
        if (deleted[slot]) {
          if (firstDeleted < 0) {
            firstDeleted = slot;
          }
        } else if (keys[slot].equals(key)) {
          values[slot] = value;
          return;
        }
      }
      if (firstDeleted >= 0) {
        keys[firstDeleted] = key;
        values[firstDeleted] = value;
        deleted[firstDeleted] = false;
        size++;
        return;
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
        if (!deleted[slot] && keys[slot].equals(key)) {
          return (V) values[slot];
        }
      }
      return null;
    }

    public boolean remove(K key) {
      int index = Math.floorMod(key.hashCode(), keys.length);
      for (int i = 0; i < keys.length; i++) {
        int slot = (index + i) % keys.length;
        if (keys[slot] == null) {
          return false;
        }
        if (!deleted[slot] && keys[slot].equals(key)) {
          deleted[slot] = true;
          values[slot] = null;
          size--;
          return true;
        }
      }
      return false;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    HashTableWithRemove<String, Integer> table = new HashTableWithRemove<>(11);
    table.put("one", 1);
    table.put("two", 2);
    table.put("three", 3);
    check(table.remove("two"));
    check(!table.remove("two"));
    check(table.get("one") == 1);
    check(table.get("three") == 3);
    check(table.get("two") == null);
    table.put("four", 4);
    check(table.get("four") == 4);
    check(table.size() == 3);
    System.out.println("Exercise_27_22 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
