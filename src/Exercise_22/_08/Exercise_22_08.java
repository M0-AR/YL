package Exercise_22._08;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_22_08 {
    private static final int CHUNK_SIZE = 10_000;

    public static void main(String[] args) throws IOException {
        generatePrimes(10_000_000_000L, Path.of("PrimeNumbers.dat"));
    }

    static void generatePrimes(long limit, Path file) throws IOException {
        try (RandomAccessFile data = new RandomAccessFile(file.toFile(), "rw")) {
            long alignedLength = data.length() - data.length() % Long.BYTES;
            if (alignedLength != data.length()) {
                data.setLength(alignedLength);
            }

            long last = alignedLength == 0 ? 1 : readLast(data);
            if (last < 2) {
                data.setLength(0);
                data.writeLong(2);
                last = 2;
            }

            long candidate = last == 2 ? 3 : last + 2;
            if (candidate % 2 == 0) {
                candidate++;
            }

            while (candidate <= limit) {
                if (isPrime(candidate, data)) {
                    data.seek(data.length());
                    data.writeLong(candidate);
                }
                candidate += 2;
            }
        }
    }

    static boolean isPrime(long value, RandomAccessFile primes) throws IOException {
        long[] buffer = new long[CHUNK_SIZE];
        primes.seek(0);
        while (true) {
            int read = readChunk(primes, buffer);
            if (read == 0) {
                return true;
            }
            for (int i = 0; i < read; i++) {
                long divisor = buffer[i];
                if (divisor * divisor > value) {
                    return true;
                }
                if (value % divisor == 0) {
                    return false;
                }
            }
            if (read < CHUNK_SIZE) {
                return true;
            }
        }
    }

    private static int readChunk(RandomAccessFile data, long[] buffer) throws IOException {
        int count = 0;
        while (count < buffer.length) {
            try {
                buffer[count++] = data.readLong();
            } catch (IOException endOfFile) {
                break;
            }
        }
        return count;
    }

    private static long readLast(RandomAccessFile data) throws IOException {
        data.seek(data.length() - Long.BYTES);
        return data.readLong();
    }

    static boolean fileExists(Path file) {
        return Files.exists(file);
    }
}
