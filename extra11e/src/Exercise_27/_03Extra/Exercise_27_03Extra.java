package Exercise_27._03Extra;

import java.util.ArrayList;
import java.util.List;

public class Exercise_27_03Extra {
	static class ChainedHashSet<E> {
		private final List<E>[] buckets;
		private int size;

		@SuppressWarnings({"unchecked", "rawtypes"})
		ChainedHashSet(int capacity) {
			buckets = new List[capacity];
			for (int i = 0; i < buckets.length; i++) {
				buckets[i] = new ArrayList<>();
			}
		}

		boolean add(E value) {
			int index = Math.floorMod(value.hashCode(), buckets.length);
			if (buckets[index].contains(value)) {
				return false;
			}
			buckets[index].add(value);
			size++;
			return true;
		}

		boolean contains(E value) {
			return buckets[Math.floorMod(value.hashCode(), buckets.length)].contains(value);
		}

		boolean remove(E value) {
			int index = Math.floorMod(value.hashCode(), buckets.length);
			if (buckets[index].remove(value)) {
				size--;
				return true;
			}
			return false;
		}

		int size() {
			return size;
		}

		int bucketCount() {
			return buckets.length;
		}
	}

	public static void main(String[] args) {
		ChainedHashSet<String> set = new ChainedHashSet<>(4);
		if (!set.add("Aa") || !set.add("BB") || !set.add("Cc")) {
			throw new IllegalStateException("Unexpected duplicate");
		}
		if (set.add("Aa")) {
			throw new IllegalStateException("Duplicate accepted");
		}
		if (!set.contains("BB")) {
			throw new IllegalStateException("Missing value");
		}
		if (!set.remove("Aa") || set.remove("Aa")) {
			throw new IllegalStateException("Unexpected remove");
		}
		if (set.size() != 2) {
			throw new IllegalStateException("Unexpected size");
		}
		System.out.println("Size: " + set.size() + ", buckets: " + set.bucketCount());
		System.out.println("Exercise_27_03Extra OK");
	}
}
