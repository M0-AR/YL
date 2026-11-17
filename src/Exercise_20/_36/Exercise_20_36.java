package Exercise_20._36;

import java.util.PriorityQueue;

public class Exercise_20_36 {
  public static int kthLargest(int[] values, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int value : values) {
      heap.add(value);
      if (heap.size() > k) {
        heap.remove();
      }
    }
    return heap.peek();
  }

  public static void main(String[] args) {
    check(kthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2) == 5);
    check(kthLargest(new int[]{9, 8, 7, 6}, 4) == 6);
    System.out.println("Exercise_20_36 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
