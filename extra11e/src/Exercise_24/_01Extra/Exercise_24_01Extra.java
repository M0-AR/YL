package Exercise_24._01Extra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Exercise_24_01Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter 10 numbers: ");
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			arrayList.add(input.nextInt());
		}

		reverse(arrayList);
		for (int value : arrayList) {
			System.out.print(value + " ");
		}
		System.out.println();

		LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
		reverse(linkedList);
		for (int value : linkedList) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	public static <E> ArrayList<E> reverse(ArrayList<E> list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			E temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
		}
		return list;
	}

	public static <E> LinkedList<E> reverse(LinkedList<E> list) {
		ListIterator<E> front = list.listIterator();
		ListIterator<E> back = list.listIterator(list.size());
		for (int i = 0; i < list.size() / 2; i++) {
			E first = front.next();
			E last = back.previous();
			front.set(last);
			back.set(first);
		}
		return list;
	}
}
