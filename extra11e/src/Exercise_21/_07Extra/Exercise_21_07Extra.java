package Exercise_21._07Extra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_21_07Extra {
	record WordCount(String word, int count) {
	}

	public static void main(String[] args) {
		String text = "Good morning. Have a good class. Have a good visit. Have fun!";
		Map<String, Integer> counts = new HashMap<>();
		for (String raw : text.toLowerCase().split("[^a-z]+")) {
			if (!raw.isEmpty()) {
				counts.merge(raw, 1, Integer::sum);
			}
		}
		List<WordCount> list = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			list.add(new WordCount(entry.getKey(), entry.getValue()));
		}
		list.sort(Comparator.comparingInt(WordCount::count).reversed()
				.thenComparing(WordCount::word));
		System.out.println(list);
		if (!list.get(0).equals(new WordCount("good", 3))) {
			throw new IllegalStateException("Unexpected first word: " + list.get(0));
		}
		if (!list.get(1).equals(new WordCount("have", 3))) {
			throw new IllegalStateException("Unexpected second word: " + list.get(1));
		}
		System.out.println("Exercise_21_07Extra OK");
	}
}
