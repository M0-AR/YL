package Exercise_27._16;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise_27_16 {
  private static class Entry<K, V> {
    private final K key;
    private V value;

    private Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class ChainedHashTable<K, V> {
    private final List<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public ChainedHashTable(int capacity) {
      buckets = (List<Entry<K, V>>[]) new List<?>[capacity];
      for (int i = 0; i < buckets.length; i++) {
        buckets[i] = new ArrayList<>();
      }
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      List<Entry<K, V>> bucket = buckets[index(key)];
      for (Entry<K, V> entry : bucket) {
        if (entry.key.equals(key)) {
          entry.value = value;
          return;
        }
      }
      bucket.add(new Entry<>(key, value));
      size++;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
      for (Entry<K, V> entry : buckets[index(key)]) {
        if (entry.key.equals(key)) {
          return entry.value;
        }
      }
      return null;
    }

    public V remove(K key) {
      List<Entry<K, V>> bucket = buckets[index(key)];
      for (int i = 0; i < bucket.size(); i++) {
        if (bucket.get(i).key.equals(key)) {
          V value = bucket.remove(i).value;
          size--;
          return value;
        }
      }
      return null;
    }

    public int size() {
      return size;
    }

    private int index(K key) {
      return Math.floorMod(key.hashCode(), buckets.length);
    }
  }

  public static void main(String[] args) {
    ChainedHashTable<String, Integer> table = new ChainedHashTable<>(16);
    table.put("one", 1);
    table.put("two", 2);
    table.put("one", 11);
    check(table.get("one") == 11);
    check(table.get("two") == 2);
    check(table.get("three") == null);
    check(table.remove("two") == 2);
    check(table.get("two") == null);
    check(table.size() == 1);
    System.out.println("Exercise_27_16 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
