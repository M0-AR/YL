package Exercise_22._12;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class Exercise_22_12 {
    private static final int LAST_COUNT = 100;

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "PrimeNumbers.dat");
        for (long prime : readLastPrimes(file, LAST_COUNT)) {
            System.out.println(prime);
        }
    }

    static long[] readLastPrimes(Path file, int count) throws IOException {
        try (RandomAccessFile data = new RandomAccessFile(file.toFile(), "r")) {
            long available = data.length() / Long.BYTES;
            int size = (int) Math.min(count, available);
            long[] primes = new long[size];
            data.seek(data.length() - (long) size * Long.BYTES);
            for (int i = 0; i < size; i++) {
                primes[i] = data.readLong();
            }
            return primes;
        }
    }
}
