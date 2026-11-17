package Exercise_30._10;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Exercise_30_10 {
    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
        set.add(1);
        AtomicBoolean failed = new AtomicBoolean();
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(1);

        Thread modifier = new Thread(() -> {
            await(start);
            int value = 2;
            for (int i = 0; i < 200; i++) {
                set.add(value++);
                sleep(1);
            }
            done.countDown();
        });

        Thread iteratorThread = new Thread(() -> {
            await(start);
            while (done.getCount() > 0) {
                try {
                    synchronized (set) {
                        Iterator<Integer> iterator = set.iterator();
                        while (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                } catch (RuntimeException exception) {
                    failed.set(true);
                    break;
                }
                sleep(1);
            }
        });

        modifier.start();
        iteratorThread.start();
        start.countDown();
        done.await();
        modifier.join();
        iteratorThread.join();
        System.out.println("Set size: " + set.size());
        System.out.println("ConcurrentModificationException occurred: " + failed.get());
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
