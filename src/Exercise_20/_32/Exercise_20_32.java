package Exercise_20._32;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_20_32 {
  public static double evaluate(String expression) {
    Deque<Double> stack = new ArrayDeque<>();
    for (String token : expression.trim().split("\s+")) {
      switch (token) {
        case "+" -> stack.push(stack.pop() + stack.pop());
        case "-" -> {
          double right = stack.pop();
          double left = stack.pop();
          stack.push(left - right);
        }
        case "*" -> stack.push(stack.pop() * stack.pop());
        case "/" -> {
          double right = stack.pop();
          double left = stack.pop();
          stack.push(left / right);
        }
        default -> stack.push(Double.parseDouble(token));
      }
    }
    return stack.pop();
  }

  public static void main(String[] args) {
    check(Math.abs(evaluate("3 4 + 2 *") - 14.0) < 0.000001);
    check(Math.abs(evaluate("5 1 2 + 4 * + 3 -") - 14.0) < 0.000001);
    System.out.println("Exercise_20_32 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
