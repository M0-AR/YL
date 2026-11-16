// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._13;

import java.io.*;
import java.util.*;

public class Exercise_12_13 {

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java filename");
			System.exit(1);
		}


		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File " + args[0] + " does not exist");
			System.exit(2);
		}

		int characters = 0;
		int words = 0;
		int lines = 0;

		try (

			Scanner input = new Scanner(file);
		) {
			while (input.hasNext()) {
				lines++;
				String line = input.nextLine();
				characters += line.length();
			}
		}

		try (

			Scanner input = new Scanner(file);
		) {
			while (input.hasNext()) {
				String line = input.next();
				words++;
			}
		}


		System.out.println("File " + file.getName() + " has");
		System.out.println(characters + " characters");
		System.out.println(words + " words");
		System.out.println(lines + " lines");
	}
}
