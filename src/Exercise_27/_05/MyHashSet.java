// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_27._05;

public class MyHashSet<E> implements MySet<E> {
	MyMap<E, E> map;

	public MyHashSet() {
		map = new MyHashMap<>();
	}

	public MyHashSet(int initialCapacity) {
		map = new MyHashMap(initialCapacity);
	}

	public MyHashSet(int initialCapacity, float loadFactorThreshold) {
		map = new MyHashMap(initialCapacity, loadFactorThreshold);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean contains(E e) {
		return map.containsKey(e) && map.containsValue(e);
	}

	@Override
	public E add(E e){
		E element = map.put(e, e);
		return element;
	}

	@Override
	public boolean remove(E e) {
		if (!map.containsKey(e))
			return false;

		map.remove(e);
		return true;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new MyHashSetIterator(this);
	}


	private class MyHashSetIterator implements java.util.Iterator<E> {

		private java.util.ArrayList<E> list;
		private int current = 0;
		private MyHashSet<E> set;


		public MyHashSetIterator(MyHashSet<E> set) {
			this.set = set;
			list = setToList();
		}

		@Override
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}


		public void remove() {

			set.remove(list.get(current));
			list.remove(current);
		}
	}


	private java.util.ArrayList<E> setToList() {
		java.util.ArrayList<E> list = new java.util.ArrayList<>();
		java.util.Set<E> set = map.keySet();

		for (E e: set) {
			list.add(e);
		}

		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		java.util.ArrayList<E> list = setToList();

		for (int i = 0; i < list.size() - 1; i++) {
			builder.append(list.get(i) + ", ");
		}

		if (list.size() == 0)
			builder.append("]");
		else
			builder.append(list.get(list.size() - 1) + "]");
		return builder.toString();
	}
}
