// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_20._12;

public class Exercise_20_12 {
	public static void main(String[] args) throws CloneNotSupportedException {

		MyPriorityQueue<String> queue1 = new MyPriorityQueue<>();
		queue1.offer("Oklahoma");
		queue1.offer("Indiana");
		queue1.offer("Georgia");
		queue1.offer("Texas");


		MyPriorityQueue<String> queue2 = queue1.clone();


		queue1.offer("New York");
		queue1.offer("New Jersey");


		queue2.remove();


		System.out.print("Queue1: ");
		while (queue1.size() > 0) {
			System.out.print(queue1.remove() + " ");
		}
		System.out.println();

		System.out.print("Queue2: ");
		while (queue2.size() > 0) {
			System.out.print(queue2.remove() + " ");
		}
		System.out.println();
	}
}
