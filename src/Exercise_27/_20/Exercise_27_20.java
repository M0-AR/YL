package Exercise_27._20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise_27_20 {
  private static class Entry<K, V> {
    private final K key;
    private V value;

    private Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class ResizableHashTable<K, V> {
    private List<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public ResizableHashTable(int capacity) {
      buckets = (List<Entry<K, V>>[]) new List<?>[capacity];
      for (int i = 0; i < buckets.length; i++) {
        buckets[i] = new ArrayList<>();
      }
    }

    public void put(K key, V value) {
      Objects.requireNonNull(key);
      if (size + 1 > buckets.length * 0.75) {
        resize();
      }
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

    public V get(K key) {
      for (Entry<K, V> entry : buckets[index(key)]) {
        if (entry.key.equals(key)) {
          return entry.value;
        }
      }
      return null;
    }

    public int size() {
      return size;
    }

    public int capacity() {
      return buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
      List<Entry<K, V>>[] old = buckets;
      buckets = (List<Entry<K, V>>[]) new List<?>[old.length * 2];
      for (int i = 0; i < buckets.length; i++) {
        buckets[i] = new ArrayList<>();
      }
      size = 0;
      for (List<Entry<K, V>> bucket : old) {
        for (Entry<K, V> entry : bucket) {
          put(entry.key, entry.value);
        }
      }
    }

    private int index(K key) {
      return Math.floorMod(key.hashCode(), buckets.length);
    }
  }

  public static void main(String[] args) {
    ResizableHashTable<Integer, Integer> table = new ResizableHashTable<>(4);
    for (int i = 0; i < 20; i++) {
      table.put(i, i * i);
    }
    check(table.size() == 20);
    check(table.capacity() > 4);
    for (int i = 0; i < 20; i++) {
      check(table.get(i) == i * i);
    }
    System.out.println("Exercise_27_20 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
