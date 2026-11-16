package atlas.enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public final class Multithreading {
    private Multithreading() {
    }

    public static int countWithAtomics(int threads, int increments) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread worker = new Thread(() -> {
                for (int j = 0; j < increments; j++) counter.incrementAndGet();
            });
            workers.add(worker);
            worker.start();
        }
        for (Thread worker : workers) worker.join();
        return counter.get();
    }

    public static long parallelSum(int[] values) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try {
            int chunk = (values.length + 3) / 4;
            List<Future<Long>> futures = new ArrayList<>();
            for (int start = 0; start < values.length; start += chunk) {
                int from = start;
                int to = Math.min(values.length, start + chunk);
                futures.add(pool.submit(() -> {
                    long sum = 0;
                    for (int i = from; i < to; i++) sum += values[i];
                    return sum;
                }));
            }
            long total = 0;
            for (Future<Long> future : futures) total += future.get();
            return total;
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("atomic count = " + countWithAtomics(8, 100_000));

        int[] values = new int[1_000_000];
        for (int i = 0; i < values.length; i++) values[i] = i + 1;
        System.out.println("parallel sum = " + parallelSum(values));

        CompletableFuture<Integer> answer = CompletableFuture
            .supplyAsync(() -> 20)
            .thenApply(value -> value + 22);
        System.out.println("composed = " + answer.get());
    }
}
