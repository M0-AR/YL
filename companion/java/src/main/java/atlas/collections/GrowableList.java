package atlas.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class GrowableList<E> implements Iterable<E> {
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
        Objects.checkIndex(index, size);
        return (E) data[index];
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        @SuppressWarnings("unchecked")
        E removed = (E) data[index];
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(data, index + 1, data, index, moved);
        }
        data[--size] = null;
        return removed;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (cursor >= size) throw new NoSuchElementException();
                return GrowableList.this.get(cursor++);
            }
        };
    }
}
