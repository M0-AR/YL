// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._08;

import java.util.*;
import java.io.*;

public class Exercise_21_08 {
	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java Exercise_21_08 textFileName");
			System.exit(1);
		}


		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("The file " + args[0] + " does not exists.");
			System.exit(1);
		}


		Map<String, Integer> map = new TreeMap<>();

		try (
			Scanner input = new Scanner(file);
		) {
			while (input.hasNext()) {
				String[] words = input.nextLine().split("[ \n\t\r\"\'.,;:!?()]");
				store(map, words);
			}
		}


		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();


		for (Map.Entry<String, Integer> entry: entrySet)
			System.out.println(entry.getValue() + "\t" + entry.getKey());
	}


	private static void store(Map<String, Integer> map, String[] words) {
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();

			if (key.length() > 0 && Character.isLetter(key.charAt(0))) {
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
	}
}
