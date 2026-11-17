package Exercise_20._34;

import java.util.ArrayDeque;
import java.util.Queue;

public class Exercise_20_34 {
  public static int lastRemaining(int count, int step) {
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= count; i++) {
      queue.add(i);
    }
    while (queue.size() > 1) {
      for (int i = 1; i < step; i++) {
        queue.add(queue.remove());
      }
      queue.remove();
    }
    return queue.remove();
  }

  public static void main(String[] args) {
    check(lastRemaining(7, 3) == 4);
    check(lastRemaining(5, 2) == 3);
    System.out.println("Exercise_20_34 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
