package Exercise_07._06Extra;

public class Exercise_07_06Extra {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise_07_06Extra string");
			System.exit(1);
		}

		if (isPalindrome(args[0])) {
			System.out.println(args[0] + " is a palindrome");
		}
		else {
			System.out.println(args[0] + " is not a palindrome");
		}
	}

	public static boolean isPalindrome(String s) {
		int low = 0;
		int high = s.length() - 1;
		while (low < high) {
			if (Character.toLowerCase(s.charAt(low))
				!= Character.toLowerCase(s.charAt(high))) {
				return false;
			}
			low++;
			high--;
		}
		return true;
	}
}
