package Exercise_23._14;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Exercise_23_14 {
    private static final int[] SIZES = {
        5_000_000, 10_000_000, 15_000_000, 20_000_000, 25_000_000, 30_000_000
    };
    private static final int CHUNK_SIZE = 1_000_000;

    public static void main(String[] args) throws IOException {
        System.out.printf("%-14s%14s%14s%14s%14s%14s%14s%n", "File size",
                "5,000,000", "10,000,000", "15,000,000", "20,000,000",
                "25,000,000", "30,000,000");
        long[] times = new long[SIZES.length];
        for (int i = 0; i < SIZES.length; i++) {
            Path directory = Files.createTempDirectory("external-sort");
            try {
                Path input = directory.resolve("input.dat");
                Path output = directory.resolve("output.dat");
                generate(input, SIZES[i]);
                long start = System.currentTimeMillis();
                externalSort(input, output, CHUNK_SIZE);
                times[i] = System.currentTimeMillis() - start;
            } finally {
                deleteDirectory(directory);
            }
        }
        System.out.printf("%-14s", "Time (ms)");
        for (long time : times) {
            System.out.printf("%14d", time);
        }
        System.out.println();
    }

    public static void externalSort(Path input, Path output, int chunkSize) throws IOException {
        List<Path> runs = new ArrayList<>();
        try (DataInputStream data = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(input)))) {
            boolean done = false;
            while (!done) {
                int[] chunk = new int[chunkSize];
                int count = 0;
                while (count < chunk.length) {
                    try {
                        int value = data.readInt();
                        chunk[count++] = value;
                    } catch (IOException endOfFile) {
                        done = true;
                        break;
                    }
                }
                if (count == 0) {
                    break;
                }
                Arrays.sort(chunk, 0, count);
                Path run = Files.createTempFile("run-", ".dat");
                try (DataOutputStream writer = new DataOutputStream(
                        new BufferedOutputStream(Files.newOutputStream(run)))) {
                    for (int i = 0; i < count; i++) {
                        writer.writeInt(chunk[i]);
                    }
                }
                runs.add(run);
            }
        }

        merge(runs, output);
        for (Path run : runs) {
            Files.deleteIfExists(run);
        }
    }

    private static void merge(List<Path> runs, Path output) throws IOException {
        class Source {
            final DataInputStream data;
            int value;

            Source(Path path) throws IOException {
                data = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)));
                value = data.readInt();
            }

            boolean advance() throws IOException {
                try {
                    value = data.readInt();
                    return true;
                } catch (IOException endOfFile) {
                    data.close();
                    return false;
                }
            }
        }

        List<Source> sources = new ArrayList<>();
        try {
            PriorityQueue<Source> queue = new PriorityQueue<>(
                    Comparator.comparingInt(source -> source.value));
            for (Path run : runs) {
                Source source = new Source(run);
                sources.add(source);
                queue.add(source);
            }

            try (DataOutputStream writer = new DataOutputStream(
                    new BufferedOutputStream(Files.newOutputStream(output)))) {
                while (!queue.isEmpty()) {
                    Source source = queue.poll();
                    writer.writeInt(source.value);
                    if (source.advance()) {
                        queue.add(source);
                    }
                }
            }
        } finally {
            for (Source source : sources) {
                try {
                    source.data.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void generate(Path input, int count) throws IOException {
        Random random = new Random(23);
        try (DataOutputStream data = new DataOutputStream(
                new BufferedOutputStream(Files.newOutputStream(input)))) {
            for (int i = 0; i < count; i++) {
                data.writeInt(random.nextInt(Integer.MAX_VALUE));
            }
        }
    }

    private static void deleteDirectory(Path directory) throws IOException {
        List<Path> paths = new ArrayList<>();
        try (var stream = Files.walk(directory)) {
            stream.forEach(paths::add);
        }
        for (int i = paths.size() - 1; i >= 0; i--) {
            Files.deleteIfExists(paths.get(i));
        }
    }

}
