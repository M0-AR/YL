package Exercise_24._26;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Exercise_24_26 {
  public static class MyArrayList<E> implements Iterable<E> {
    private Object[] data = new Object[4];
    private int size;

    public void add(E value) {
      if (size == data.length) {
        data = Arrays.copyOf(data, data.length * 2);
      }
      data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
      }
      return (E) data[index];
    }

    public int size() {
      return size;
    }

    public Iterator<E> iterator() {
      return new Iterator<>() {
        private int cursor;
        private int lastReturned = -1;

        public boolean hasNext() {
          return cursor < size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          lastReturned = cursor;
          return (E) data[cursor++];
        }

        public void remove() {
          if (lastReturned < 0) {
            throw new IllegalStateException();
          }
          System.arraycopy(data, lastReturned + 1, data, lastReturned, size - lastReturned - 1);
          data[--size] = null;
          cursor = lastReturned;
          lastReturned = -1;
        }
      };
    }
  }

  public static void main(String[] args) {
    MyArrayList<Integer> list = new MyArrayList<>();
    for (int i = 1; i <= 6; i++) {
      list.add(i);
    }
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (iterator.next() % 2 == 0) {
        iterator.remove();
      }
    }
    check(list.size() == 3);
    check(list.get(0) == 1 && list.get(1) == 3 && list.get(2) == 5);
    System.out.println("Exercise_24_26 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
