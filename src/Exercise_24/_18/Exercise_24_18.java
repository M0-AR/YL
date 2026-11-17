package Exercise_24._18;

import java.util.ArrayList;
import java.util.List;

public class Exercise_24_18 {
  private static class Node<E> {
    private E value;
    private Node<E> next;

    private Node(E value) {
      this.value = value;
    }
  }

  public static class SinglyLinkedList<E> {
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

    public void reverse() {
      Node<E> previous = null;
      Node<E> current = head;
      while (current != null) {
        Node<E> next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }
      head = previous;
    }

    public List<E> toList() {
      List<E> result = new ArrayList<>();
      for (Node<E> current = head; current != null; current = current.next) {
        result.add(current.value);
      }
      return result;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    for (int i = 1; i <= 4; i++) {
      list.add(i);
    }
    list.reverse();
    check(list.toList().equals(List.of(4, 3, 2, 1)));
    check(list.size() == 4);
    System.out.println("Exercise_24_18 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
