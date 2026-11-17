package Exercise_19._19;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_19_19 {
  public static class MinStack<E extends Comparable<E>> {
    private final Deque<E> values = new ArrayDeque<>();
    private final Deque<E> minimums = new ArrayDeque<>();

    public void push(E value) {
      values.push(value);
      if (minimums.isEmpty() || value.compareTo(minimums.peek()) <= 0) {
        minimums.push(value);
      }
    }

    public E pop() {
      E value = values.pop();
      if (value.equals(minimums.peek())) {
        minimums.pop();
      }
      return value;
    }

    public E min() {
      return minimums.peek();
    }

    public int size() {
      return values.size();
    }
  }

  public static void main(String[] args) {
    MinStack<Integer> stack = new MinStack<>();
    stack.push(5);
    stack.push(3);
    stack.push(7);
    stack.push(2);
    check(stack.min() == 2);
    stack.pop();
    check(stack.min() == 3);
    stack.pop();
    check(stack.min() == 3);
    System.out.println("Exercise_19_19 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
