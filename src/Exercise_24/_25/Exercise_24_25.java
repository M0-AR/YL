package Exercise_24._25;

import java.util.ArrayList;
import java.util.List;

public class Exercise_24_25 {
  public static class SortedPriorityQueue<E extends Comparable<E>> {
    private final List<E> values = new ArrayList<>();

    public void add(E value) {
      int index = 0;
      while (index < values.size() && values.get(index).compareTo(value) <= 0) {
        index++;
      }
      values.add(index, value);
    }

    public E peek() {
      return values.isEmpty() ? null : values.get(0);
    }

    public E remove() {
      return values.isEmpty() ? null : values.remove(0);
    }

    public int size() {
      return values.size();
    }
  }

  public static void main(String[] args) {
    SortedPriorityQueue<Integer> queue = new SortedPriorityQueue<>();
    queue.add(5);
    queue.add(1);
    queue.add(3);
    queue.add(1);
    check(queue.peek() == 1);
    check(queue.remove() == 1);
    check(queue.remove() == 1);
    check(queue.remove() == 3);
    check(queue.remove() == 5);
    check(queue.size() == 0);
    System.out.println("Exercise_24_25 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
