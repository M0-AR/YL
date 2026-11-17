package Exercise_30._09;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Exercise_30_09 {
    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch failed = new CountDownLatch(1);

        Thread modifier = new Thread(() -> {
            await(start);
            int value = 2;
            while (failed.getCount() > 0) {
                set.add(value++);
                sleep(10);
            }
        });

        Thread iteratorThread = new Thread(() -> {
            await(start);
            try {
                Iterator<Integer> iterator = set.iterator();
                while (true) {
                    if (iterator.hasNext()) {
                        iterator.next();
                    } else {
                        iterator = set.iterator();
                    }
                    sleep(10);
                }
            } catch (java.util.ConcurrentModificationException exception) {
                System.out.println("ConcurrentModificationException demonstrated");
                failed.countDown();
            }
        });

        modifier.start();
        iteratorThread.start();
        start.countDown();
        failed.await();
        modifier.join(2000);
        iteratorThread.join(2000);
        System.out.println("Set size: " + set.size());
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
