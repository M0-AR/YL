package Exercise_21._01Extra;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise_21_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String text = input.nextLine();

		Set<Character> vowels = new HashSet<>();
		Set<Character> consonants = new HashSet<>();
		for (char ch : "AEIOU".toCharArray()) {
			vowels.add(ch);
		}

		Set<Character> foundVowels = new HashSet<>();
		Set<Character> foundConsonants = new HashSet<>();
		for (int i = 0; i < text.length(); i++) {
			char ch = Character.toUpperCase(text.charAt(i));
			if (!Character.isLetter(ch)) {
				continue;
			}
			if (vowels.contains(ch)) {
				foundVowels.add(ch);
			}
			else {
				foundConsonants.add(ch);
			}
		}

		System.out.println("The number of vowels is " + foundVowels.size());
		System.out.println("The number of consonants is "
			+ foundConsonants.size());
	}
}
