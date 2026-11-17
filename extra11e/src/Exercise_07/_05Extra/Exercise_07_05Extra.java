package Exercise_07._05Extra;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise_07_05Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the first string: ");
		String first = input.nextLine();
		System.out.print("Enter the second string: ");
		String second = input.nextLine();

		System.out.println(first + " and " + second + " are "
			+ (isAnagram(first, second) ? "anagram." : "not anagram."));
	}

	public static boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		return Arrays.equals(chars1, chars2);
	}
}
