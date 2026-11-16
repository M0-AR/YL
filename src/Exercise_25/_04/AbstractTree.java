// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._04;

public abstract class AbstractTree<E>
		implements Tree<E> {
	@Override
	public void inorder() {
	}

	@Override
	public void postorder() {
	}

	@Override
	public void preorder() {
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}
}
