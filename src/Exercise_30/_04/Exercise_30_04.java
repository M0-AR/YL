package Exercise_30._04;

import java.util.concurrent.CountDownLatch;

public class Exercise_30_04 {
    private static Integer sum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Unsynchronized sum: " + run(false));
        System.out.println("Synchronized sum: " + run(true));
    }

    private static int run(boolean synchronizedAdd) throws InterruptedException {
        sum = 0;
        CountDownLatch ready = new CountDownLatch(1000);
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                ready.countDown();
                try {
                    start.await();
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    return;
                }
                if (synchronizedAdd) {
                    synchronized (lock) {
                        sum = sum + 1;
                    }
                } else {
                    sum = sum + 1;
                }
            });
            threads[i].start();
        }
        ready.await();
        start.countDown();
        for (Thread thread : threads) {
            thread.join();
        }
        return sum;
    }
}
