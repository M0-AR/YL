package Exercise_20._01Extra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Exercise_20_01Extra {
	public static void main(String[] args) {
		List<String> words = List.of("pear", "fig", "banana", "apple", "kiwi", "date");
		PriorityQueue<String> queue = new PriorityQueue<>(
				Comparator.comparingInt(String::length).thenComparing(String::compareTo));
		queue.addAll(words);
		List<String> sorted = new ArrayList<>();
		while (!queue.isEmpty()) {
			sorted.add(queue.poll());
		}
		List<String> expected = List.of("fig", "date", "kiwi", "pear", "apple", "banana");
		if (!sorted.equals(expected)) {
			throw new IllegalStateException("Unexpected order: " + sorted);
		}
		System.out.println(sorted);
		System.out.println("Exercise_20_01Extra OK");
	}
}
