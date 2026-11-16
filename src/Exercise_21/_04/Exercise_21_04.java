// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._04;

import java.util.*;
import java.io.*;

public class Exercise_21_04 {
	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);


		System.out.print("Enter a text file name: ");
		String fileName = input.next();


		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("The file " + fileName + " does not exist.");
			System.exit(1);
		}


		Set<Character> set = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));
		int vowels = 0;
		int consonants = 0;


		try (
			Scanner inputFile = new Scanner(file);
		) {
			while (inputFile.hasNext()) {
				String line = inputFile.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (set.contains(Character.toUpperCase(line.charAt(i))))
						vowels++;
					else if (Character.isLetter(line.charAt(i)))
						consonants++;
				}
			}
		}


		System.out.println("The file " + fileName + " has " + vowels +
			" vowels and " + consonants + " consonants.");
	}
}
