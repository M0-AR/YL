// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_27._11;

import java.util.ArrayList;
import java.util.Set;

public class Exercise_27_11 {
	public static void main(String[] args) {

		Set<String> set = new java.util.HashSet<>();
		set.add("Mark");
		set.add("Smith");
		set.add("Anderson");
		set.add("Sally");
		System.out.println("Elements in the set: " + set);


		ArrayList<String> list = setToList(set);
		System.out.println("Elements in the ArrayList: " + list);
		System.out.println("Elements at ArrayList index 2: " + list.get(2));

	}


	public static <E> ArrayList<E> setToList(Set<E> s) {

		ArrayList<E> list = new ArrayList<>();


		for (E e: s) {
			list.add(e);
		}

		return list;
	}
}
