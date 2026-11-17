package Exercise_20._30;

import java.util.ArrayDeque;
import java.util.Queue;

public class Exercise_20_30 {
  public static int totalWait(int[] serviceTimes) {
    Queue<Integer> queue = new ArrayDeque<>();
    for (int time : serviceTimes) {
      queue.add(time);
    }
    int elapsed = 0;
    int wait = 0;
    while (!queue.isEmpty()) {
      wait += elapsed;
      elapsed += queue.remove();
    }
    return wait;
  }

  public static void main(String[] args) {
    check(totalWait(new int[]{3, 2, 4}) == 8);
    check(totalWait(new int[]{5}) == 0);
    System.out.println("Exercise_20_30 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
