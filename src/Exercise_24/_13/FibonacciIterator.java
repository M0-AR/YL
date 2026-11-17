package Exercise_24._13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FibonacciIterator implements Iterator<Long> {
    private final long limit;
    private long previous;
    private long current = 1;

    public FibonacciIterator(long limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return current <= limit;
    }

    @Override
    public Long next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        long value = current;
        long next = previous + current;
        previous = current;
        current = next;
        return value;
    }
}
