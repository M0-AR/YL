// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_24._06;

import java.util.Comparator;

public class Exercise_24_06 {
	public static void main(String[] args) {

		GeometricObject object1 = new Circle(5);
		GeometricObject object2 = new Rectangle(4, 5);
		GeometricObject object3 = new Circle(6.5);
		GeometricObject object4 = new Rectangle(2.4, 5);


		MyPriorityQueue<GeometricObject> priorityQueue
			= new MyPriorityQueue<>(new GeometricObjectComparator());


		priorityQueue.enqueue(object1);
		priorityQueue.enqueue(object2);
		priorityQueue.enqueue(object3);
		priorityQueue.enqueue(object4);


		System.out.printf("%.2f ", priorityQueue.dequeue().getArea());
		System.out.printf("%.2f ", priorityQueue.dequeue().getArea());
		System.out.printf("%.2f ", priorityQueue.dequeue().getArea());
		System.out.printf("%.2f ", priorityQueue.dequeue().getArea());
		System.out.println();
	}
}
