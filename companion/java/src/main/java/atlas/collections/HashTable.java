package atlas.collections;

import java.util.Objects;

public final class HashTable<K, V> {
    private static final class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] buckets = new Entry[16];
    private int size;

    public V get(K key) {
        Objects.requireNonNull(key);
        for (Entry<K, V> entry = buckets[indexFor(key)]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    public V put(K key, V value) {
        Objects.requireNonNull(key);
        int index = indexFor(key);
        for (Entry<K, V> entry = buckets[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                V previous = entry.value;
                entry.value = value;
                return previous;
            }
        }
        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;
        if (size > buckets.length * 0.75) resize();
        return null;
    }

    public V remove(K key) {
        Objects.requireNonNull(key);
        int index = indexFor(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) buckets[index] = current.next;
                else previous.next = current.next;
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.length;
    }

    public int longestBucket() {
        int longest = 0;
        for (Entry<K, V> head : buckets) {
            int length = 0;
            for (Entry<K, V> entry = head; entry != null; entry = entry.next) length++;
            longest = Math.max(longest, length);
        }
        return longest;
    }

    private int indexFor(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    private void resize() {
        Entry<K, V>[] old = buckets;
        buckets = new Entry[old.length * 2];
        size = 0;
        for (Entry<K, V> head : old) {
            for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                put(entry.key, entry.value);
            }
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("java", 1995);
        table.put("records", 2020);
        System.out.println(table.get("java"));
        System.out.println("size=" + table.size() + ", buckets=" + table.capacity());
    }
}
