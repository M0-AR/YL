// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._07;

import java.util.*;

public class Exercise_21_07 {
	public static void main(String[] args) {

		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";


		Map<String, Integer> map = new HashMap<>();


		String[] words = text.split("[ \n\t\r.,;:!?()]");
		for (String word: words) {
			String key = word.toLowerCase();

			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}


		ArrayList<WordOccurrence> list = new ArrayList<>();


		for (Map.Entry<String, Integer> entry: map.entrySet())
			list.add(new WordOccurrence(entry.getKey(), entry.getValue()));


		Collections.sort(list);


		System.out.println(list);
	}
}
