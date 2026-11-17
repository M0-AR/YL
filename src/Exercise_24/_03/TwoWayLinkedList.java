package Exercise_24._03;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> extends AbstractSequentialList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public TwoWayLinkedList() {
    }

    public TwoWayLinkedList(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    public E getFirst() {
        return first == null ? null : first.element;
    }

    public E getLast() {
        return last == null ? null : last.element;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == size) {
            Node<E> node = new Node<>(e);
            if (last == null) {
                first = last = node;
            } else {
                node.previous = last;
                last.next = node;
                last = node;
            }
        } else {
            Node<E> next = nodeAt(index);
            Node<E> previous = next.previous;
            Node<E> node = new Node<>(e);
            node.next = next;
            node.previous = previous;
            next.previous = node;
            if (previous == null) {
                first = node;
            } else {
                previous.next = node;
            }
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        return nodeAt(index).element;
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            if (object == null ? node.element == null : object.equals(node.element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (Node<E> node = last; node != null; node = node.previous) {
            if (object == null ? node.element == null : object.equals(node.element)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        Node<E> node = nodeAt(index);
        E element = node.element;
        Node<E> previous = node.previous;
        Node<E> next = node.next;
        if (previous == null) {
            first = next;
        } else {
            previous.next = next;
        }
        if (next == null) {
            last = previous;
        } else {
            next.previous = previous;
        }
        size--;
        modCount++;
        return element;
    }

    @Override
    public E set(int index, E e) {
        Node<E> node = nodeAt(index);
        E old = node.element;
        node.element = e;
        return old;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListIterator<>() {
            private int cursor = index;
            private int lastReturned = -1;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = cursor;
                return get(cursor++);
            }

            @Override
            public boolean hasPrevious() {
                return cursor > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                lastReturned = --cursor;
                return get(cursor);
            }

            @Override
            public int nextIndex() {
                return cursor;
            }

            @Override
            public int previousIndex() {
                return cursor - 1;
            }

            @Override
            public void remove() {
                if (lastReturned < 0) {
                    throw new IllegalStateException();
                }
                TwoWayLinkedList.this.remove(lastReturned);
                cursor = lastReturned;
                lastReturned = -1;
            }

            @Override
            public void set(E e) {
                if (lastReturned < 0) {
                    throw new IllegalStateException();
                }
                TwoWayLinkedList.this.set(lastReturned, e);
            }

            @Override
            public void add(E e) {
                TwoWayLinkedList.this.add(cursor++, e);
                lastReturned = -1;
            }
        };
    }

    private Node<E> nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
        }
        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        Node(E element) {
            this.element = element;
        }
    }
}
