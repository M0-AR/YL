// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_24._16;

public abstract class MyAbstractList<E> implements MyList<E> {
	protected int size = 0;


	protected MyAbstractList() {
	}


	protected MyAbstractList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	@Override
	public void add(E e) {
		add(size, e);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override

	public boolean remove(E e) {
		if (indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		}
		else
			return false;
	}
}
