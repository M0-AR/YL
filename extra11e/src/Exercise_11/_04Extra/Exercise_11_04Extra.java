package Exercise_11._04Extra;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise_11_04Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the arguments: ");
		ArrayList<String> arguments = getArguments(input.nextLine());
		for (String argument : arguments) {
			System.out.println(argument);
		}
	}

	public static ArrayList<String> getArguments(String line) {
		ArrayList<String> arguments = new ArrayList<>();
		StringBuilder argument = new StringBuilder();
		boolean inQuotes = false;

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (ch == '"') {
				inQuotes = !inQuotes;
			}
			else if (Character.isWhitespace(ch) && !inQuotes) {
				if (argument.length() > 0) {
					arguments.add(argument.toString());
					argument.setLength(0);
				}
			}
			else {
				argument.append(ch);
			}
		}

		if (argument.length() > 0) {
			arguments.add(argument.toString());
		}
		return arguments;
	}
}
