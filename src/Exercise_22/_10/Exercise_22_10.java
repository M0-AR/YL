package Exercise_22._10;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class Exercise_22_10 {
    private static final long[] LIMITS = {
        10L, 100L, 1_000L, 10_000L, 100_000L, 1_000_000L,
        10_000_000L, 100_000_000L, 1_000_000_000L, 10_000_000_000L
    };

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "PrimeNumbers.dat");
        countPrimes(file);
    }

    static void countPrimes(Path file) throws IOException {
        try (RandomAccessFile data = new RandomAccessFile(file.toFile(), "r")) {
            long count = 0;
            int limit = 0;
            while (limit < LIMITS.length) {
                long value;
                try {
                    value = data.readLong();
                } catch (IOException endOfFile) {
                    break;
                }
                if (value > LIMITS[limit]) {
                    while (limit < LIMITS.length && LIMITS[limit] < value) {
                        System.out.printf("<= %d: %d%n", LIMITS[limit], count);
                        limit++;
                    }
                }
                count++;
            }
            while (limit < LIMITS.length) {
                System.out.printf("<= %d: %d%n", LIMITS[limit], count);
                limit++;
            }
        }
    }
}
