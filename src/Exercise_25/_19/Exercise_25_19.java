package Exercise_25._19;

import Exercise_25._18.HuffmanArchive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_25_19 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise_25_19 sourcefile targetfile");
            return;
        }

        Path source = Path.of(args[0]);
        Path target = Path.of(args[1]);
        try {
            HuffmanArchive.decompress(source, target);
            System.out.println("Decompressed " + source + " to " + target + ".");
            System.out.println("Source bytes: " + Files.size(source));
            System.out.println("Target bytes: " + Files.size(target));
        } catch (IOException exception) {
            System.out.println("Decompression failed: " + exception.getMessage());
        }
    }
}
