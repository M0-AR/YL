package Exercise_20._33;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_20_33 {
  public static boolean isBalanced(String expression) {
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);
      if (ch == '(' || ch == '[' || ch == '{') {
        stack.push(ch);
      } else if (ch == ')' || ch == ']' || ch == '}') {
        if (stack.isEmpty()) {
          return false;
        }
        char open = stack.pop();
        if ((ch == ')' && open != '(')
            || (ch == ']' && open != '[')
            || (ch == '}' && open != '{')) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    check(isBalanced("{[()]}"));
    check(isBalanced("(a + b) * [c - d]"));
    check(!isBalanced("([)]"));
    check(!isBalanced("((("));
    System.out.println("Exercise_20_33 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
