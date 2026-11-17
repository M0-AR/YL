package Exercise_30._11;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Exercise_30_11 {
    private static final Object firstLock = new Object();
    private static final Object secondLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(() -> {
            synchronized (firstLock) {
                sleep(100);
                synchronized (secondLock) {
                    System.out.println("First thread acquired both locks");
                }
            }
        }, "FirstThread");

        Thread second = new Thread(() -> {
            synchronized (secondLock) {
                sleep(100);
                synchronized (firstLock) {
                    System.out.println("Second thread acquired both locks");
                }
            }
        }, "SecondThread");

        first.start();
        second.start();
        first.join(1000);
        second.join(1000);

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] deadlocked = bean.findDeadlockedThreads();
        if (deadlocked == null) {
            System.out.println("No deadlock detected");
        } else {
            System.out.println("Deadlock demonstrated between "
                    + first.getName() + " and " + second.getName());
        }
        System.exit(0);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
