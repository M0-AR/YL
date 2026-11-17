package Exercise_24._02Extra;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Exercise_24_02Extra {
	public static void main(String[] args) {
		MyList<String> list = new MyLinkedList<>();

		list.add("America");
		System.out.println("(1) " + list);

		list.add(0, "Canada");
		System.out.println("(2) " + list);

		list.add("Russia");
		System.out.println("(3) " + list);

		list.add("France");
		System.out.println("(4) " + list);

		list.add(2, "Germany");
		System.out.println("(5) " + list);

		list.add(5, "Norway");
		System.out.println("(6) " + list);

		list.remove("Canada");
		System.out.println("(7) " + list);

		list.remove(2);
		System.out.println("(8) " + list);

		list.remove(list.size() - 1);
		System.out.print("(9) " + list + "\n(10) ");

		for (String s : list) {
			System.out.print(s.toUpperCase() + " ");
		}

		System.out.print("\n(11) ");
		list.forEach(e -> System.out.print(e.toLowerCase() + " "));

		System.out.print("\n(12) ");
		list.stream().sorted().forEach(e -> System.out.print(e + " "));

		list.clear();
		System.out.println("\n(13) The list size is " + list.size());
	}

	public interface MyList<E> extends List<E> {
	}

	public static class MyLinkedList<E> extends AbstractList<E>
			implements MyList<E> {
		private Node<E> head;
		private Node<E> tail;
		private int size;

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean add(E e) {
			Node<E> node = new Node<>(e);
			if (size == 0) {
				head = node;
				tail = node;
				node.next = head;
			}
			else {
				tail.next = node;
				tail = node;
				tail.next = head;
			}
			size++;
			modCount++;
			return true;
		}

		@Override
		public void add(int index, E e) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException("Index: " + index);
			}
			if (index == size) {
				add(e);
				return;
			}

			Node<E> node = new Node<>(e);
			if (index == 0) {
				node.next = head;
				head = node;
				tail.next = head;
			}
			else {
				Node<E> before = nodeAt(index - 1);
				node.next = before.next;
				before.next = node;
			}
			size++;
			modCount++;
		}

		@Override
		public E get(int index) {
			return nodeAt(index).element;
		}

		@Override
		public E set(int index, E e) {
			Node<E> node = nodeAt(index);
			E old = node.element;
			node.element = e;
			return old;
		}

		@Override
		public E remove(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Index: " + index);
			}

			E removed;
			if (index == 0) {
				removed = head.element;
				if (size == 1) {
					head = null;
					tail = null;
				}
				else {
					head = head.next;
					tail.next = head;
				}
			}
			else {
				Node<E> before = nodeAt(index - 1);
				Node<E> node = before.next;
				removed = node.element;
				before.next = node.next;
				if (node == tail) {
					tail = before;
				}
				tail.next = head;
			}
			size--;
			modCount++;
			return removed;
		}

		@Override
		public boolean remove(Object o) {
			Node<E> previous = tail;
			Node<E> current = head;
			for (int i = 0; i < size; i++) {
				if (current.element == null ? o == null
						: current.element.equals(o)) {
					if (current == head) {
						remove(0);
					}
					else {
						previous.next = current.next;
						if (current == tail) {
							tail = previous;
						}
						tail.next = head;
						size--;
						modCount++;
					}
					return true;
				}
				previous = current;
				current = current.next;
			}
			return false;
		}

		@Override
		public void clear() {
			head = null;
			tail = null;
			size = 0;
			modCount++;
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterator<>() {
				private Node<E> next = head;
				private int remaining = size;

				@Override
				public boolean hasNext() {
					return remaining > 0;
				}

				@Override
				public E next() {
					if (!hasNext()) {
						throw new NoSuchElementException();
					}
					E element = next.element;
					next = next.next;
					remaining--;
					return element;
				}
			};
		}

		private Node<E> nodeAt(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Index: " + index);
			}
			Node<E> current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current;
		}

		private static class Node<E> {
			private E element;
			private Node<E> next;

			private Node(E element) {
				this.element = element;
			}
		}
	}
}
