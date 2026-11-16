// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_27._05;

public interface MySet<E> extends java.lang.Iterable<E> {

	public void clear();


	public boolean contains(E e);


	public E add(E e);


	public boolean remove(E e);


	public boolean isEmpty();


	public int size();
}
