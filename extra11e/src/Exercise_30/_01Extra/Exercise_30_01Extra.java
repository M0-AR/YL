package Exercise_30._01Extra;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise_30_01Extra {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
		AtomicInteger total = new AtomicInteger();
		Thread producer = new Thread(() -> {
			try {
				for (int i = 1; i <= 10; i++) {
					queue.put(i);
				}
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		});
		Thread consumer = new Thread(() -> {
			try {
				for (int i = 1; i <= 10; i++) {
					total.addAndGet(queue.take());
				}
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		});
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();
		if (total.get() != 55) {
			throw new IllegalStateException("Unexpected total: " + total.get());
		}
		System.out.println("Consumed sum: " + total.get());
		System.out.println("Exercise_30_01Extra OK");
	}
}
