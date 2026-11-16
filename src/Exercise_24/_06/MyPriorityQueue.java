// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_24._06;

import java.util.Comparator;

public class MyPriorityQueue<E>{
	private Comparator<? super E> comparator;
	private Heap<E> heap;

	MyPriorityQueue(Comparator<? super E> comparator) {
		this.comparator = comparator;
		this.heap = new Heap<>(comparator);
	}

	public void enqueue(E newObject) {
		heap.add(newObject);
	}

	public E dequeue() {
		return heap.remove();
	}

	public int getSize() {
		return heap.getSize();
	}
}
