package Exercise_21._06Extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_21_06Extra {
	public static void main(String[] args) {
		int[] values = {3, 5, 3, 7, 5, 9, 7, 3};
		Map<Integer, Integer> counts = new HashMap<>();
		for (int value : values) {
			counts.merge(value, 1, Integer::sum);
		}
		int minimum = Collections.min(counts.values());
		List<Integer> least = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			if (entry.getValue() == minimum) {
				least.add(entry.getKey());
			}
		}
		Collections.sort(least);
		System.out.println("Least frequent: " + least + " (count " + minimum + ")");
		if (!least.equals(List.of(9)) || minimum != 1) {
			throw new IllegalStateException("Unexpected least frequent values");
		}
		System.out.println("Exercise_21_06Extra OK");
	}
}
