package Exercise_20._38;

import java.util.Collections;
import java.util.PriorityQueue;

public class Exercise_20_38 {
  private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
  private final PriorityQueue<Integer> upper = new PriorityQueue<>();

  public void add(int value) {
    if (lower.isEmpty() || value <= lower.peek()) {
      lower.add(value);
    } else {
      upper.add(value);
    }
    if (lower.size() > upper.size() + 1) {
      upper.add(lower.remove());
    } else if (upper.size() > lower.size()) {
      lower.add(upper.remove());
    }
  }

  public double median() {
    if (lower.size() == upper.size()) {
      return (lower.peek() + upper.peek()) / 2.0;
    }
    return lower.peek();
  }

  public static void main(String[] args) {
    Exercise_20_38 finder = new Exercise_20_38();
    finder.add(1);
    finder.add(2);
    finder.add(3);
    check(finder.median() == 2.0);
    finder.add(4);
    check(finder.median() == 2.5);
    System.out.println("Exercise_20_38 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
