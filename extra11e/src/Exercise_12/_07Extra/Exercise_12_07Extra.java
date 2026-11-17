package Exercise_12._07Extra;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_12_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File " + filename + " does not exist");
			return;
		}

		try {
			List<String> lines = Files.readAllLines(file.toPath());
			List<String> result = new ArrayList<>();
			for (String line : lines) {
				result.add(replaceLineNumber(line));
			}
			Files.write(file.toPath(), result,
				java.nio.charset.StandardCharsets.UTF_8);
		}
		catch (Exception ex) {
			System.out.println("Unable to process " + filename);
			return;
		}

		System.out.println("Done");
	}

	private static String replaceLineNumber(String line) {
		int index = 0;
		while (index < line.length()
				&& Character.isWhitespace(line.charAt(index))) {
			index++;
		}

		int digitStart = index;
		while (index < line.length() && Character.isDigit(line.charAt(index))) {
			index++;
		}

		if (digitStart == index
				|| (index < line.length()
					&& !Character.isWhitespace(line.charAt(index)))) {
			return line;
		}

		char[] characters = line.toCharArray();
		for (int i = digitStart; i < index; i++) {
			characters[i] = ' ';
		}
		return new String(characters);
	}
}
