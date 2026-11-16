// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._02;

import java.util.*;
import java.io.*;

public class Exercise_21_02 {
	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java fileName");
			System.exit(1);
		}


		File textFile = new File(args[0]);
		if (!textFile.exists()) {
			System.out.println("The file " + args[0] + " does not exist.");
			System.exit(2);
		}


		TreeSet<String> set = new TreeSet<>();


		try (
			Scanner input = new Scanner(textFile);
		) {
			while (input.hasNext()) {
				String[] words = input.nextLine().split("[ \n\t\r.,;:!?()-]");
				for (String word: words) {
					if (word.length() > 0)
						set.add(word.toLowerCase());
				}
			}
		}


		System.out.println(set);
	}
}
