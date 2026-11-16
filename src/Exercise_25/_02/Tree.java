// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_25._02;

public interface Tree<E> extends Iterable<E> {

	public boolean search(E e);



	public boolean insert(E e);



	public boolean delete(E e);


	public void inorder();


	public void postorder();


	public void preorder();


	public void breadthFirstTraversal();


	public int getSize();


	public boolean isEmpty();
}
