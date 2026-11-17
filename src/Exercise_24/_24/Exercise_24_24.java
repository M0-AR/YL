package Exercise_24._24;

import java.util.ArrayList;
import java.util.List;

public class Exercise_24_24 {
  public static class ArrayDequeCustom<E> {
    private Object[] data = new Object[8];
    private int head;
    private int size;

    public void addFirst(E value) {
      ensureCapacity();
      head = (head - 1 + data.length) % data.length;
      data[head] = value;
      size++;
    }

    public void addLast(E value) {
      ensureCapacity();
      data[(head + size) % data.length] = value;
      size++;
    }

    @SuppressWarnings("unchecked")
    public E removeFirst() {
      if (size == 0) {
        throw new IllegalStateException("empty deque");
      }
      E value = (E) data[head];
      data[head] = null;
      head = (head + 1) % data.length;
      size--;
      return value;
    }

    @SuppressWarnings("unchecked")
    public E removeLast() {
      if (size == 0) {
        throw new IllegalStateException("empty deque");
      }
      int index = (head + size - 1) % data.length;
      E value = (E) data[index];
      data[index] = null;
      size--;
      return value;
    }

    public List<E> toList() {
      List<E> result = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        @SuppressWarnings("unchecked")
        E value = (E) data[(head + i) % data.length];
        result.add(value);
      }
      return result;
    }

    public int size() {
      return size;
    }

    private void ensureCapacity() {
      if (size == data.length) {
        Object[] larger = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
          larger[i] = data[(head + i) % data.length];
        }
        data = larger;
        head = 0;
      }
    }
  }

  public static void main(String[] args) {
    ArrayDequeCustom<Integer> deque = new ArrayDequeCustom<>();
    deque.addLast(2);
    deque.addLast(3);
    deque.addFirst(1);
    deque.addFirst(0);
    check(deque.toList().equals(List.of(0, 1, 2, 3)));
    check(deque.removeFirst() == 0);
    check(deque.removeLast() == 3);
    check(deque.toList().equals(List.of(1, 2)));
    check(deque.size() == 2);
    System.out.println("Exercise_24_24 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
