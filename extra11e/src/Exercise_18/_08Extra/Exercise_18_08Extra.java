package Exercise_18._08Extra;

public class Exercise_18_08Extra {
	public static void main(String[] args) {
		check(isPalindrome("racecar"), "racecar");
		check(isPalindrome("java"), "java");
		int sum = sumDigits(12345);
		if (sum != 15) {
			throw new IllegalStateException("Unexpected digit sum: " + sum);
		}
		System.out.println("Digit sum of 12345: " + sum);
		System.out.println("Exercise_18_08Extra OK");
	}

	static void check(boolean actual, String text) {
		boolean expected = text.equals(new StringBuilder(text).reverse().toString());
		if (actual != expected) {
			throw new IllegalStateException("Unexpected palindrome result for " + text);
		}
		System.out.println(text + " palindrome: " + actual);
	}

	static boolean isPalindrome(String text) {
		if (text.length() <= 1) {
			return true;
		}
		if (text.charAt(0) != text.charAt(text.length() - 1)) {
			return false;
		}
		return isPalindrome(text.substring(1, text.length() - 1));
	}

	static int sumDigits(int value) {
		if (value == 0) {
			return 0;
		}
		return value % 10 + sumDigits(value / 10);
	}
}
