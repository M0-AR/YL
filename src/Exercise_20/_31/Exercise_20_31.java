package Exercise_20._31;

import java.util.PriorityQueue;

public class Exercise_20_31 {
  public static class Task {
    private final String name;
    private final int priority;
    private final int sequence;

    public Task(String name, int priority, int sequence) {
      this.name = name;
      this.priority = priority;
      this.sequence = sequence;
    }

    public String getName() {
      return name;
    }

    public int getPriority() {
      return priority;
    }

    public int getSequence() {
      return sequence;
    }
  }

  public static void main(String[] args) {
    PriorityQueue<Task> queue = new PriorityQueue<>((a, b) -> {
      int byPriority = Integer.compare(b.getPriority(), a.getPriority());
      return byPriority != 0 ? byPriority : Integer.compare(a.getSequence(), b.getSequence());
    });
    queue.add(new Task("email", 2, 0));
    queue.add(new Task("backup", 5, 1));
    queue.add(new Task("meeting", 5, 2));
    queue.add(new Task("cleanup", 1, 3));
    StringBuilder order = new StringBuilder();
    while (!queue.isEmpty()) {
      order.append(queue.remove().getName()).append(' ');
    }
    check(order.toString().trim().equals("backup meeting email cleanup"));
    System.out.println("Exercise_20_31 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
