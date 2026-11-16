package Exercise_17._17;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class BitOutputStream implements AutoCloseable {
    private final OutputStream output;
    private int buffer;
    private int bitCount;
    private boolean closed;

    public BitOutputStream(File file) throws IOException {
        this(new FileOutputStream(file));
    }

    public BitOutputStream(OutputStream output) {
        this.output = Objects.requireNonNull(output, "output");
    }

    public void writeBit(char bit) throws IOException {
        if (bit != '0' && bit != '1') {
            throw new IllegalArgumentException("bit must be '0' or '1'");
        }
        if (closed) {
            throw new IOException("stream is closed");
        }

        buffer = (buffer << 1) | (bit - '0');
        bitCount++;
        if (bitCount == 8) {
            output.write(buffer);
            buffer = 0;
            bitCount = 0;
        }
    }

    public void writeBit(String bits) throws IOException {
        Objects.requireNonNull(bits, "bits");
        for (int i = 0; i < bits.length(); i++) {
            writeBit(bits.charAt(i));
        }
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        if (bitCount > 0) {
            buffer <<= 8 - bitCount;
            output.write(buffer);
        }
        closed = true;
        output.close();
    }
}
