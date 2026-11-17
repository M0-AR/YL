package Exercise_20._29;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_20_29 {
  public static boolean isPalindrome(String text) {
    Deque<Character> stack = new ArrayDeque<>();
    String normalized = text.toLowerCase();
    for (int i = 0; i < normalized.length(); i++) {
      char ch = normalized.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        stack.push(ch);
      }
    }
    for (int i = 0; i < normalized.length(); i++) {
      char ch = normalized.charAt(i);
      if (Character.isLetterOrDigit(ch) && stack.pop() != ch) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    check(isPalindrome("A man, a plan, a canal: Panama"));
    check(!isPalindrome("Java"));
    check(isPalindrome("No lemon, no melon"));
    System.out.println("Exercise_20_29 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
