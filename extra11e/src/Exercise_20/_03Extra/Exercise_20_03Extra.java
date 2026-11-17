package Exercise_20._03Extra;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

public class Exercise_20_03Extra {
	public static void main(String[] args) {
		Map<String, String> capitals = new LinkedHashMap<>();
		capitals.put("Texas", "Austin");
		capitals.put("California", "Sacramento");
		capitals.put("New York", "Albany");
		capitals.put("Florida", "Tallahassee");
		Queue<String> questions = new ArrayDeque<>(capitals.keySet());
		String[] answers = {"Austin", "Sacramento", "Albany", "Miami"};
		int correct = 0;
		int index = 0;
		while (!questions.isEmpty()) {
			String state = questions.remove();
			String expected = capitals.get(state);
			String answer = answers[index++];
			boolean ok = expected.equalsIgnoreCase(answer);
			if (ok) {
				correct++;
			}
			System.out.println(state + ": " + answer + " -> "
					+ (ok ? "correct" : "expected " + expected));
		}
		if (correct != 3) {
			throw new IllegalStateException("Unexpected score: " + correct);
		}
		System.out.println("Correct answers: " + correct + "/" + capitals.size());
		System.out.println("Exercise_20_03Extra OK");
	}
}
