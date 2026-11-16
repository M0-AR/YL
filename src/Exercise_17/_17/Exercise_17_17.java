package Exercise_17._17;

import java.io.IOException;
import java.nio.file.Path;

public class Exercise_17_17 {
    private static final String BITS = "010000100100001001101";

    public static void main(String[] args) {
        Path output = Path.of(args.length > 0 ? args[0] : "Exercise17_17.dat");

        try (BitOutputStream stream = new BitOutputStream(output.toFile())) {
            stream.writeBit(BITS);
            System.out.println("Wrote " + BITS.length() + " bits to " + output.toAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Unable to write " + output + ": " + ex.getMessage());
            System.exit(1);
        }
    }
}
