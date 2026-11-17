package Exercise_11._01Extra;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise_11_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String s = input.nextLine();
		System.out.print("Enter delimiters: ");
		String delimiters = input.nextLine();

		ArrayList<String> items = split(s, delimiters);
		for (String item : items) {
			System.out.print(item + " ");
		}
	}

	public static ArrayList<String> split(String s, String delimiters) {
		ArrayList<String> result = new ArrayList<>();
		StringBuilder item = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (delimiters.indexOf(ch) >= 0) {
				if (item.length() > 0) {
					result.add(item.toString());
					item.setLength(0);
				}
			}
			else {
				item.append(ch);
			}
		}

		if (item.length() > 0) {
			result.add(item.toString());
		}
		return result;
	}
}
