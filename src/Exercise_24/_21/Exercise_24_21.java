package Exercise_24._21;

import java.util.ArrayList;
import java.util.List;

public class Exercise_24_21 {
  private static class Node<E> {
    private E value;
    private Node<E> previous;
    private Node<E> next;

    private Node(E value) {
      this.value = value;
    }
  }

  public static class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addLast(E value) {
      Node<E> node = new Node<>(value);
      if (tail == null) {
        head = node;
        tail = node;
      } else {
        node.previous = tail;
        tail.next = node;
        tail = node;
      }
      size++;
    }

    public void addFirst(E value) {
      Node<E> node = new Node<>(value);
      if (head == null) {
        head = node;
        tail = node;
      } else {
        node.next = head;
        head.previous = node;
        head = node;
      }
      size++;
    }

    public boolean remove(E value) {
      for (Node<E> current = head; current != null; current = current.next) {
        if (current.value.equals(value)) {
          if (current.previous == null) {
            head = current.next;
          } else {
            current.previous.next = current.next;
          }
          if (current.next == null) {
            tail = current.previous;
          } else {
            current.next.previous = current.previous;
          }
          size--;
          return true;
        }
      }
      return false;
    }

    public List<E> forward() {
      List<E> result = new ArrayList<>();
      for (Node<E> current = head; current != null; current = current.next) {
        result.add(current.value);
      }
      return result;
    }

    public List<E> backward() {
      List<E> result = new ArrayList<>();
      for (Node<E> current = tail; current != null; current = current.previous) {
        result.add(current.value);
      }
      return result;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    list.addLast(1);
    list.addLast(3);
    list.addFirst(0);
    list.addLast(4);
    check(list.forward().equals(List.of(0, 1, 3, 4)));
    check(list.backward().equals(List.of(4, 3, 1, 0)));
    check(list.remove(3));
    check(list.forward().equals(List.of(0, 1, 4)));
    check(list.backward().equals(List.of(4, 1, 0)));
    check(list.size() == 3);
    System.out.println("Exercise_24_21 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
