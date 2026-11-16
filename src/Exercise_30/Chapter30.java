package Exercise_30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Chapter30 {
    public static long parallelSum(int[] values, int workers) {
        if (values == null) {
            throw new IllegalArgumentException("values are required");
        }
        if (workers < 1) {
            throw new IllegalArgumentException("workers must be positive");
        }
        if (values.length == 0) {
            return 0L;
        }
        int chunks = Math.min(workers, values.length);
        int size = (values.length + chunks - 1) / chunks;
        List<Callable<Long>> tasks = new ArrayList<>();
        for (int start = 0; start < values.length; start += size) {
            int begin = start;
            int end = Math.min(values.length, start + size);
            tasks.add(() -> {
                long total = 0L;
                for (int index = begin; index < end; index++) {
                    total += values[index];
                }
                return total;
            });
        }
        ExecutorService pool = Executors.newFixedThreadPool(chunks);
        try {
            long total = 0L;
            for (Future<Long> future : pool.invokeAll(tasks)) {
                total += future.get();
            }
            return total;
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while summing", error);
        } catch (ExecutionException error) {
            throw new IllegalStateException("chunk failed", error.getCause());
        } finally {
            pool.shutdown();
        }
    }

    public static <T> List<T> taskResults(List<Callable<T>> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("tasks are required");
        }
        if (tasks.isEmpty()) {
            return List.of();
        }
        ExecutorService pool = Executors.newFixedThreadPool(Math.min(tasks.size(), 4));
        try {
            List<T> results = new ArrayList<>();
            for (Future<T> future : pool.invokeAll(tasks)) {
                results.add(future.get());
            }
            return Collections.unmodifiableList(results);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while collecting", error);
        } catch (ExecutionException error) {
            throw new IllegalStateException("task failed", error.getCause());
        } finally {
            pool.shutdown();
        }
    }

    public static int safeCount(int threads, int perThread) {
        if (threads < 1) {
            throw new IllegalArgumentException("threads must be positive");
        }
        if (perThread < 0) {
            throw new IllegalArgumentException("perThread must be non-negative");
        }
        AtomicInteger counter = new AtomicInteger();
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int worker = 0; worker < threads; worker++) {
            tasks.add(() -> {
                for (int step = 0; step < perThread; step++) {
                    counter.incrementAndGet();
                }
                return null;
            });
        }
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            for (Future<Void> future : pool.invokeAll(tasks)) {
                future.get();
            }
            return counter.get();
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while counting", error);
        } catch (ExecutionException error) {
            throw new IllegalStateException("counter task failed", error.getCause());
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] values = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println(parallelSum(values, 3));
        System.out.println(taskResults(List.of(
                () -> "alpha".toUpperCase(),
                () -> "beta".toUpperCase(),
                () -> "gamma".toUpperCase())));
        System.out.println(safeCount(4, 250));
    }
}
