package Exercise_24._23;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_24_23 {
  public static class QueueWithStacks<E> {
    private final Deque<E> input = new ArrayDeque<>();
    private final Deque<E> output = new ArrayDeque<>();

    public void enqueue(E value) {
      input.push(value);
    }

    public E dequeue() {
      move();
      return output.pop();
    }

    public E peek() {
      move();
      return output.peek();
    }

    public int size() {
      return input.size() + output.size();
    }

    private void move() {
      if (output.isEmpty()) {
        while (!input.isEmpty()) {
          output.push(input.pop());
        }
      }
    }
  }

  public static void main(String[] args) {
    QueueWithStacks<Integer> queue = new QueueWithStacks<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    check(queue.peek() == 1);
    check(queue.dequeue() == 1);
    queue.enqueue(4);
    check(queue.dequeue() == 2);
    check(queue.dequeue() == 3);
    check(queue.dequeue() == 4);
    check(queue.size() == 0);
    System.out.println("Exercise_24_23 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
