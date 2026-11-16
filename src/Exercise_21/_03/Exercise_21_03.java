// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_21._03;

import java.util.*;
import java.io.*;

public class Exercise_21_03 {
	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java sourceFile");
			System.exit(1);
		}


		File file = new File(args[0]);
		if (file.exists()) {
			System.out.println("The number of keywords in " + args[0]
				+ " is " + countKeywords(file));
		}
		else {
			System.out.println("File " + args[0] + " does not exist");
		}
	}


	public static int countKeywords(File file) throws Exception {

		String[] keywordString = {"abstract", "assert", "boolean",
			"break", "byte", "case", "catch", "char", "class", "const",
			"continue", "default", "do", "double", "else", "enum",
			"extends", "for", "final", "finally", "float", "goto",
			"if", "implements", "import", "instanceof", "int",
			"interface", "long", "native", "new", "package", "private",
			"protected", "public", "return", "short", "static",
			"strictfp", "super", "switch", "synchronized", "this",
			"throw", "throws", "transient", "try", "void", "volatile",
			"while", "true", "false", "null"};

		Set<String> keywordSet =
			new HashSet<>(Arrays.asList(keywordString));
		int count = 0;

		Scanner input = new Scanner(file);

		while (input.hasNext()) {
			String word = input.next();
			if (word.equals("//")) {
				input.nextLine();
			}
			else if (word.contains("\"")) {
				String nextWord;
				do {
					nextWord = input.next();
				} while (!nextWord.contains("\""));
			}
			else if (word.contains("/*")) {
				String nextWord;
				do {
					nextWord = input.next();
				} while (!nextWord.contains("*/"));
			}
			else if (keywordSet.contains(word))
				count++;
		}

		return count;
	}
}
