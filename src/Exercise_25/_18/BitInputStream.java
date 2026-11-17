package Exercise_25._18;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class BitInputStream implements AutoCloseable {
    private final InputStream input;
    private int buffer;
    private int bitsRemaining;
    private boolean closed;

    public BitInputStream(InputStream input) {
        this.input = Objects.requireNonNull(input, "input");
    }

    public int readBit() throws IOException {
        if (closed) {
            throw new IOException("stream is closed");
        }
        if (bitsRemaining == 0) {
            buffer = input.read();
            if (buffer == -1) {
                return -1;
            }
            bitsRemaining = 8;
        }
        int bit = (buffer >> (bitsRemaining - 1)) & 1;
        bitsRemaining--;
        return bit;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        input.close();
    }
}
