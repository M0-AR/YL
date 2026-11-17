package Exercise_24._20;

import java.util.ArrayList;
import java.util.List;

public class Exercise_24_20 {
  private static class Node<E> {
    private E value;
    private Node<E> next;

    private Node(E value) {
      this.value = value;
    }
  }

  public static class CircularLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public void add(E value) {
      Node<E> node = new Node<>(value);
      if (first == null) {
        first = node;
        last = node;
        node.next = node;
      } else {
        node.next = first;
        last.next = node;
        last = node;
      }
      size++;
    }

    public boolean remove(E value) {
      if (first == null) {
        return false;
      }
      Node<E> previous = last;
      Node<E> current = first;
      for (int i = 0; i < size; i++) {
        if (current.value.equals(value)) {
          if (size == 1) {
            first = null;
            last = null;
          } else {
            previous.next = current.next;
            if (current == first) {
              first = current.next;
            }
            if (current == last) {
              last = previous;
            }
          }
          size--;
          return true;
        }
        previous = current;
        current = current.next;
      }
      return false;
    }

    public List<E> toList() {
      List<E> result = new ArrayList<>();
      Node<E> current = first;
      for (int i = 0; i < size; i++) {
        result.add(current.value);
        current = current.next;
      }
      return result;
    }

    public int size() {
      return size;
    }
  }

  public static void main(String[] args) {
    CircularLinkedList<Integer> list = new CircularLinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    check(list.toList().equals(List.of(1, 2, 3)));
    check(list.remove(2));
    check(list.toList().equals(List.of(1, 3)));
    check(list.size() == 2);
    check(list.remove(1));
    check(list.toList().equals(List.of(3)));
    System.out.println("Exercise_24_20 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
