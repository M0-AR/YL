package Exercise_24._14;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator<Integer> {
    private final int limit;
    private int candidate = 2;

    public PrimeIterator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        while (candidate <= limit && !isPrime(candidate)) {
            candidate++;
        }
        return candidate <= limit;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return candidate++;
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
