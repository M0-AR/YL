package Exercise_24._22;

import java.util.Arrays;

public class Exercise_24_22 {
  public static class MinStack {
    private int[] values = new int[4];
    private int[] minimums = new int[4];
    private int size;

    public void push(int value) {
      if (size == values.length) {
        values = Arrays.copyOf(values, values.length * 2);
        minimums = Arrays.copyOf(minimums, minimums.length * 2);
      }
      values[size] = value;
      minimums[size] = size == 0 ? value : Math.min(value, minimums[size - 1]);
      size++;
    }

    public int pop() {
      if (size == 0) {
        throw new IllegalStateException("empty stack");
      }
      return values[--size];
    }

    public int min() {
      if (size == 0) {
        throw new IllegalStateException("empty stack");
      }
      return minimums[size - 1];
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    MinStack stack = new MinStack();
    stack.push(5);
    stack.push(2);
    stack.push(7);
    stack.push(1);
    check(stack.min() == 1);
    stack.pop();
    check(stack.min() == 2);
    stack.pop();
    check(stack.min() == 2);
    System.out.println("Exercise_24_22 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
