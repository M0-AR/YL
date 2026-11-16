// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_24._05;

public class TestQueue {
	public static void main(String[] args) {

		GenericQueue<String> queue = new GenericQueue<>();


		queue.enqueue("Tom");
		System.out.println("(1) " + queue);

		queue.enqueue("Susan");
		System.out.println("(2) " + queue);

		queue.enqueue("Kim");
		queue.enqueue("Michael");
		System.out.println("(3) " + queue);


		System.out.println("(4) " + queue.dequeue());
		System.out.println("(5) " + queue.dequeue());
		System.out.println("(6) " + queue);
	}
}
