package Exercise_12._04Extra;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exercise_12_04Extra {
	public static void main(String[] args) {
		Map<String, Integer> boyCounts = new LinkedHashMap<>();
		Map<String, Integer> girlCounts = new LinkedHashMap<>();

		for (int year = 2001; year <= 2010; year++) {
			File file = new File("babynamesranking" + year + ".txt");
			try (Scanner input = new Scanner(file)) {
				while (input.hasNext()) {
					input.nextInt();
					addCount(boyCounts, input.next(), input.nextInt());
					addCount(girlCounts, input.next(), input.nextInt());
				}
			}
			catch (Exception ex) {
				System.out.println("Unable to read " + file.getName());
				return;
			}
		}

		display("Boys", boyCounts);
		display("Girls", girlCounts);
	}

	private static void addCount(Map<String, Integer> counts,
			String name, int count) {
		counts.put(name, counts.getOrDefault(name, 0) + count);
	}

	private static void display(String title, Map<String, Integer> counts) {
		List<Map.Entry<String, Integer>> entries =
			new ArrayList<>(counts.entrySet());
		entries.sort((first, second) ->
			Integer.compare(second.getValue(), first.getValue()));

		System.out.println(title + " Ranking, Name, and Count:");
		for (int i = 0; i < entries.size(); i++) {
			Map.Entry<String, Integer> entry = entries.get(i);
			System.out.println((i + 1) + " " + entry.getKey()
				+ " " + entry.getValue());
		}
	}
}
