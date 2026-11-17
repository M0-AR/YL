package Exercise_22._14;

import java.util.ArrayList;
import java.util.List;

public class Exercise_22_14 {
    private static final int[] LIMITS = {
        8_000_000, 10_000_000, 12_000_000, 14_000_000, 16_000_000, 18_000_000
    };

    public static void main(String[] args) {
        System.out.printf("%-26s", "");
        for (int limit : LIMITS) {
            System.out.printf("%12d", limit);
        }
        System.out.println();

        long[] efficient = new long[LIMITS.length];
        long[] sieve = new long[LIMITS.length];
        long[] oddSieve = new long[LIMITS.length];
        for (int i = 0; i < LIMITS.length; i++) {
            efficient[i] = timeEfficientPrimes(LIMITS[i]);
            sieve[i] = timeSieve(LIMITS[i]);
            oddSieve[i] = timeOddSieve(LIMITS[i]);
        }

        printRow("Listing 22.5", efficient);
        printRow("Listing 22.6", sieve);
        printRow("Listing 22.7", oddSieve);
    }

    private static void printRow(String label, long[] values) {
        System.out.printf("%-26s", label);
        for (long value : values) {
            System.out.printf("%12d", value);
        }
        System.out.println();
    }

    private static long timeEfficientPrimes(int limit) {
        long start = System.nanoTime();
        efficientPrimes(limit);
        return System.nanoTime() - start;
    }

    private static long timeSieve(int limit) {
        long start = System.nanoTime();
        sieve(limit);
        return System.nanoTime() - start;
    }

    private static long timeOddSieve(int limit) {
        long start = System.nanoTime();
        oddSieve(limit);
        return System.nanoTime() - start;
    }

    public static List<Integer> efficientPrimes(int limit) {
        List<Integer> primes = new ArrayList<>();
        for (int candidate = 2; candidate <= limit; candidate++) {
            boolean prime = true;
            for (int i = 0; i < primes.size(); i++) {
                int divisor = primes.get(i);
                if ((long) divisor * divisor > candidate) {
                    break;
                }
                if (candidate % divisor == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes.add(candidate);
            }
        }
        return primes;
    }

    public static int sieve(int limit) {
        boolean[] composite = new boolean[limit + 1];
        int count = 0;
        for (int value = 2; value <= limit; value++) {
            if (!composite[value]) {
                count++;
                if ((long) value * value <= limit) {
                    for (int multiple = value * value; multiple <= limit; multiple += value) {
                        composite[multiple] = true;
                    }
                }
            }
        }
        return count;
    }

    public static int oddSieve(int limit) {
        if (limit < 2) {
            return 0;
        }
        int count = 1;
        boolean[] composite = new boolean[limit / 2 + 1];
        for (int value = 3; (long) value * value <= limit; value += 2) {
            if (!composite[value / 2]) {
                for (int multiple = value * value; multiple <= limit; multiple += 2 * value) {
                    composite[multiple / 2] = true;
                }
            }
        }
        for (int value = 3; value <= limit; value += 2) {
            if (!composite[value / 2]) {
                count++;
            }
        }
        return count;
    }
}
