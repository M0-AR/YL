package Exercise_27._30;

import java.util.HashMap;
import java.util.Map;

public class Exercise_27_30 {
  private static class Node {
    private final int key;
    private int value;
    private Node previous;
    private Node next;

    private Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
      this.capacity = capacity;
    }

    public int get(int key) {
      Node node = map.get(key);
      if (node == null) {
        return -1;
      }
      moveToFront(node);
      return node.value;
    }

    public void put(int key, int value) {
      Node node = map.get(key);
      if (node != null) {
        node.value = value;
        moveToFront(node);
        return;
      }
      if (map.size() == capacity) {
        map.remove(tail.key);
        remove(tail);
      }
      Node created = new Node(key, value);
      addFirst(created);
      map.put(key, created);
    }

    private void moveToFront(Node node) {
      if (node == head) {
        return;
      }
      remove(node);
      addFirst(node);
    }

    private void addFirst(Node node) {
      node.previous = null;
      node.next = head;
      if (head != null) {
        head.previous = node;
      }
      head = node;
      if (tail == null) {
        tail = node;
      }
    }

    private void remove(Node node) {
      if (node.previous != null) {
        node.previous.next = node.next;
      } else {
        head = node.next;
      }
      if (node.next != null) {
        node.next.previous = node.previous;
      } else {
        tail = node.previous;
      }
    }
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    check(cache.get(1) == 1);
    cache.put(3, 3);
    check(cache.get(2) == -1);
    check(cache.get(1) == 1);
    check(cache.get(3) == 3);
    System.out.println("Exercise_27_30 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
