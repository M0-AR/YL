package Exercise_27._04Extra;

import java.util.ArrayList;
import java.util.List;

public class Exercise_27_04Extra {
	static class Entry<K, V> {
		final K key;
		V value;

		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	static class ChainedHashMap<K, V> {
		private List<Entry<K, V>>[] buckets;
		private int size;

		@SuppressWarnings({"unchecked", "rawtypes"})
		ChainedHashMap(int capacity) {
			buckets = new List[capacity];
			for (int i = 0; i < buckets.length; i++) {
				buckets[i] = new ArrayList<>();
			}
		}

		void put(K key, V value) {
			Entry<K, V> entry = findEntry(key);
			if (entry != null) {
				entry.value = value;
				return;
			}
			int index = indexFor(key, buckets.length);
			buckets[index].add(new Entry<>(key, value));
			size++;
			if (size > buckets.length * 0.75) {
				rehash();
			}
		}

		V get(K key) {
			Entry<K, V> entry = findEntry(key);
			return entry == null ? null : entry.value;
		}

		int size() {
			return size;
		}

		int capacity() {
			return buckets.length;
		}

		private Entry<K, V> findEntry(K key) {
			int index = indexFor(key, buckets.length);
			for (Entry<K, V> entry : buckets[index]) {
				if (entry.key.equals(key)) {
					return entry;
				}
			}
			return null;
		}

		private int indexFor(K key, int capacity) {
			return Math.floorMod(key.hashCode(), capacity);
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		private void rehash() {
			int newCapacity = buckets.length * 2;
			while (newCapacity * 0.75 < size) {
				newCapacity *= 2;
			}
			List<Entry<K, V>>[] old = buckets;
			buckets = new List[newCapacity];
			for (int i = 0; i < buckets.length; i++) {
				buckets[i] = new ArrayList<>();
			}
			for (List<Entry<K, V>> bucket : old) {
				for (Entry<K, V> entry : bucket) {
					buckets[indexFor(entry.key, newCapacity)].add(entry);
				}
			}
		}
	}

	public static void main(String[] args) {
		ChainedHashMap<String, Integer> map = new ChainedHashMap<>(4);
		for (int i = 0; i < 10; i++) {
			map.put("K" + i, i);
		}
		map.put("K5", 50);
		if (map.size() != 10) {
			throw new IllegalStateException("Unexpected size");
		}
		if (map.get("K5") != 50 || map.get("K9") != 9) {
			throw new IllegalStateException("Unexpected value");
		}
		if (map.capacity() != 16) {
			throw new IllegalStateException("Unexpected capacity: " + map.capacity());
		}
		System.out.println("Size: " + map.size() + ", capacity: " + map.capacity());
		System.out.println("Exercise_27_04Extra OK");
	}
}
