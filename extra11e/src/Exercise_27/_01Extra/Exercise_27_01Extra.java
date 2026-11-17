package Exercise_27._01Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Exercise_27_01Extra {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Collection<String> set = new MyHashSet<>();
		set.add("Smith");
		set.add("Anderson");
		set.add("Lewis");
		set.add("Cook");
		set.add("Smith");

		MyHashSet<String> set1 = new MyHashSet<>();
		set1.add("Smith");
		set1.add("Anderson");
		set1.add("Johnson");
		set1.add("Peterson");
		set1.add("Stone");
		System.out.println("set1 is " + set1);

		Collection<String> set2 = (MyHashSet<String>)(set1.clone());
		set2.add("Jones");
		System.out.println("set1 is " + set1);
		System.out.println("set2 is " + set2);

		System.out.println(set1.containsAll(set2));
		System.out.println(set2.containsAll(set2));

		Collection<String> set3 = (MyHashSet<String>)(set1.clone());
		set3.addAll(set);
		System.out.println("(union) set3 is " + set3);

		Collection<String> set4 = (MyHashSet<String>)(set1.clone());
		set4.removeAll(set);
		System.out.println("(difference) set4 is " + set4);

		Collection<String> set5 = (MyHashSet<String>)(set1.clone());
		set5.retainAll(set);
		System.out.println("(intersection) set5 is " + set5);

		Object[] temp = set.toArray();
		System.out.println(Arrays.toString(temp));

		String[] temp1 = new String[set.size()];
		temp1 = set.toArray(temp1);
		System.out.println(Arrays.toString(temp1));
	}

	public static class MyHashSet<E> implements Collection<E>, Cloneable {
		private static final int DEFAULT_INITIAL_CAPACITY = 4;
		private static final int MAXIMUM_CAPACITY = 1 << 30;
		private static final float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

		private int capacity;
		private float loadFactorThreshold;
		private int size;
		private LinkedList<E>[] table;

		public MyHashSet() {
			this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
		}

		public MyHashSet(int initialCapacity) {
			this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		public MyHashSet(int initialCapacity, float loadFactorThreshold) {
			capacity = initialCapacity > MAXIMUM_CAPACITY
				? MAXIMUM_CAPACITY : trimToPowerOf2(initialCapacity);
			this.loadFactorThreshold = loadFactorThreshold;
			table = new LinkedList[capacity];
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean isEmpty() {
			return size == 0;
		}

		@Override
		public boolean contains(Object e) {
			int index = hash(hashCode(e));
			if (table[index] == null) {
				return false;
			}
			for (E element : table[index]) {
				if (Objects.equals(element, e)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean add(E e) {
			if (contains(e)) {
				return false;
			}
			if (size + 1 > capacity * loadFactorThreshold) {
				if (capacity == MAXIMUM_CAPACITY) {
					throw new RuntimeException("Exceeding maximum capacity");
				}
				rehash();
			}

			int index = hash(hashCode(e));
			if (table[index] == null) {
				table[index] = new LinkedList<>();
			}
			table[index].add(e);
			size++;
			return true;
		}

		@Override
		public boolean remove(Object e) {
			int index = hash(hashCode(e));
			if (table[index] == null) {
				return false;
			}
			Iterator<E> iterator = table[index].iterator();
			while (iterator.hasNext()) {
				if (Objects.equals(iterator.next(), e)) {
					iterator.remove();
					size--;
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for (Object e : c) {
				if (!contains(e)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			boolean changed = false;
			for (E e : c) {
				changed |= add(e);
			}
			return changed;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			boolean changed = false;
			for (Object e : new ArrayList<>(c)) {
				changed |= remove(e);
			}
			return changed;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			boolean changed = false;
			for (E e : new ArrayList<>(setToList())) {
				if (!c.contains(e)) {
					changed |= remove(e);
				}
			}
			return changed;
		}

		@Override
		public void clear() {
			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					table[i].clear();
				}
			}
			size = 0;
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterator<>() {
				private final ArrayList<E> elements = setToList();
				private int current;
				private int lastReturned = -1;

				@Override
				public boolean hasNext() {
					return current < elements.size();
				}

				@Override
				public E next() {
					if (!hasNext()) {
						throw new NoSuchElementException();
					}
					lastReturned = current;
					return elements.get(current++);
				}

				@Override
				public void remove() {
					if (lastReturned < 0) {
						throw new IllegalStateException();
					}
					MyHashSet.this.remove(elements.get(lastReturned));
					elements.remove(lastReturned);
					if (lastReturned < current) {
						current--;
					}
					lastReturned = -1;
				}
			};
		}

		@Override
		public Object[] toArray() {
			Object[] array = new Object[size];
			int i = 0;
			for (E e : this) {
				array[i++] = e;
			}
			return array;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T[] toArray(T[] a) {
			if (a.length < size) {
				return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
			}
			int i = 0;
			for (E e : this) {
				a[i++] = (T)e;
			}
			if (a.length > size) {
				a[size] = null;
			}
			return a;
		}

		@Override
		@SuppressWarnings({"unchecked", "rawtypes"})
		public Object clone() {
			try {
				@SuppressWarnings("unchecked")
				MyHashSet<E> copy = (MyHashSet<E>)super.clone();
				copy.table = new LinkedList[copy.capacity];
				copy.size = 0;
				for (E e : this) {
					copy.add(e);
				}
				return copy;
			}
			catch (CloneNotSupportedException ex) {
				throw new AssertionError(ex);
			}
		}

		@Override
		public String toString() {
			ArrayList<E> list = setToList();
			StringBuilder builder = new StringBuilder("[");
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					builder.append(", ");
				}
				builder.append(list.get(i));
			}
			return builder.append("]").toString();
		}

		private ArrayList<E> setToList() {
			ArrayList<E> list = new ArrayList<>();
			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					list.addAll(table[i]);
				}
			}
			return list;
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		private void rehash() {
			ArrayList<E> list = setToList();
			capacity <<= 1;
			@SuppressWarnings("unchecked")
			LinkedList<E>[] newTable = new LinkedList[capacity];
			table = newTable;
			size = 0;
			for (E e : list) {
				add(e);
			}
		}

		private int hash(int hashCode) {
			return supplementalHash(hashCode) & (capacity - 1);
		}

		private static int hashCode(Object e) {
			return e == null ? 0 : e.hashCode();
		}

		private static int supplementalHash(int h) {
			h ^= (h >>> 20) ^ (h >>> 12);
			return h ^ (h >>> 7) ^ (h >>> 4);
		}

		private int trimToPowerOf2(int initialCapacity) {
			int result = 1;
			while (result < initialCapacity) {
				result <<= 1;
			}
			return result;
		}
	}
}
