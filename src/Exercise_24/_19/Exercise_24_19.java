package Exercise_24._19;

public class Exercise_24_19 {
  private static class Node<E> {
    private E value;
    private Node<E> next;

    private Node(E value) {
      this.value = value;
    }
  }

  public static class LinkedListSearch<E> {
    private Node<E> head;
    private int size;

    public void add(E value) {
      Node<E> node = new Node<>(value);
      if (head == null) {
        head = node;
      } else {
        Node<E> current = head;
        while (current.next != null) {
          current = current.next;
        }
        current.next = node;
      }
      size++;
    }

    public boolean contains(E value) {
      return indexOf(value) >= 0;
    }

    public int indexOf(E value) {
      int index = 0;
      for (Node<E> current = head; current != null; current = current.next) {
        if (current.value.equals(value)) {
          return index;
        }
        index++;
      }
      return -1;
    }

    public int lastIndexOf(E value) {
      int index = 0;
      int result = -1;
      for (Node<E> current = head; current != null; current = current.next) {
        if (current.value.equals(value)) {
          result = index;
        }
        index++;
      }
      return result;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    LinkedListSearch<String> list = new LinkedListSearch<>();
    list.add("a");
    list.add("b");
    list.add("a");
    check(list.contains("b"));
    check(!list.contains("c"));
    check(list.indexOf("a") == 0);
    check(list.lastIndexOf("a") == 2);
    check(list.indexOf("c") == -1);
    System.out.println("Exercise_24_19 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
