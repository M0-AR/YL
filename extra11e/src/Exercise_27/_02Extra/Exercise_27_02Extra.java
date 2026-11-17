package Exercise_27._02Extra;

import java.util.Map;
import java.util.TreeMap;

public class Exercise_27_02Extra {
	static class LinearProbingMap<K, V> {
		private Object[] keys;
		private Object[] values;
		private int size;

		LinearProbingMap(int capacity) {
			keys = new Object[capacity];
			values = new Object[capacity];
		}

		void put(K key, V value) {
			int index = find(key);
			if (index >= 0) {
				values[index] = value;
				return;
			}
			if (size == keys.length) {
				throw new IllegalStateException("Map is full");
			}
			int start = Math.floorMod(key.hashCode(), keys.length);
			for (int i = 0; i < keys.length; i++) {
				int slot = (start + i) % keys.length;
				if (keys[slot] == null) {
					keys[slot] = key;
					values[slot] = value;
					size++;
					return;
				}
			}
			throw new IllegalStateException("Map is full");
		}

		@SuppressWarnings("unchecked")
		V get(K key) {
			int index = find(key);
			return index < 0 ? null : (V) values[index];
		}

		void remove(K key) {
			int index = find(key);
			if (index < 0) {
				return;
			}
			keys[index] = null;
			values[index] = null;
			size--;
			rehash();
		}

		int size() {
			return size;
		}

		Map<K, V> snapshot() {
			Map<K, V> result = new TreeMap<>(
					(a, b) -> a.toString().compareTo(b.toString()));
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != null) {
					@SuppressWarnings("unchecked")
					K key = (K) keys[i];
					@SuppressWarnings("unchecked")
					V value = (V) values[i];
					result.put(key, value);
				}
			}
			return result;
		}

		private int find(K key) {
			int start = Math.floorMod(key.hashCode(), keys.length);
			for (int i = 0; i < keys.length; i++) {
				int slot = (start + i) % keys.length;
				if (keys[slot] == null) {
					return -1;
				}
				if (keys[slot].equals(key)) {
					return slot;
				}
			}
			return -1;
		}

		private void rehash() {
			Object[] oldKeys = keys;
			Object[] oldValues = values;
			keys = new Object[oldKeys.length];
			values = new Object[oldKeys.length];
			size = 0;
			for (int i = 0; i < oldKeys.length; i++) {
				if (oldKeys[i] != null) {
					@SuppressWarnings("unchecked")
					K key = (K) oldKeys[i];
					@SuppressWarnings("unchecked")
					V value = (V) oldValues[i];
					put(key, value);
				}
			}
		}
	}

	public static void main(String[] args) {
		LinearProbingMap<String, Integer> map = new LinearProbingMap<>(4);
		map.put("Aa", 1);
		map.put("BB", 2);
		map.put("Cc", 3);
		map.put("Dd", 4);
		if (map.size() != 4) {
			throw new IllegalStateException("Unexpected size");
		}
		if (map.get("Aa") != 1 || map.get("Dd") != 4) {
			throw new IllegalStateException("Unexpected value");
		}
		map.remove("BB");
		if (map.size() != 3 || map.get("BB") != null) {
			throw new IllegalStateException("Unexpected remove");
		}
		System.out.println(map.snapshot());
		System.out.println("Exercise_27_02Extra OK");
	}
}
