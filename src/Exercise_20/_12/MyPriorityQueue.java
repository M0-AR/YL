// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_20._12;

import java.util.*;

public class MyPriorityQueue<E> extends PriorityQueue<E> implements Cloneable {
	@Override

	public MyPriorityQueue<E> clone() throws CloneNotSupportedException {
		MyPriorityQueue<E> temp = new MyPriorityQueue<>();
		temp.addAll((MyPriorityQueue<E>)super.clone());
		return temp;
	}

}
