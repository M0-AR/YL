package Exercise_24._17;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Exercise_24_17 {
  public static class MyArrayList<E> {
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

    public boolean removeAll(Collection<?> values) {
      int write = 0;
      for (int read = 0; read < size; read++) {
        if (!values.contains(data[read])) {
          data[write++] = data[read];
        }
      }
      boolean changed = write != size;
      for (int i = write; i < size; i++) {
        data[i] = null;
      }
      size = write;
      return changed;
    }
  }

  public static void main(String[] args) {
    MyArrayList<Integer> list = new MyArrayList<>();
    for (int i = 1; i <= 6; i++) {
      list.add(i);
    }
    check(list.removeAll(List.of(2, 4, 6)));
    check(list.size() == 3);
    check(list.get(0) == 1 && list.get(1) == 3 && list.get(2) == 5);
    System.out.println("Exercise_24_17 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
