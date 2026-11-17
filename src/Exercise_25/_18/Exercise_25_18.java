package Exercise_25._18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise_25_18 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise_25_18 sourcefile targetfile");
            return;
        }

        Path source = Path.of(args[0]);
        Path target = Path.of(args[1]);
        try {
            HuffmanArchive.compress(source, target);
            System.out.println("Compressed " + source + " to " + target + ".");
            System.out.println("Source bytes: " + Files.size(source));
            System.out.println("Target bytes: " + Files.size(target));
        } catch (IOException exception) {
            System.out.println("Compression failed: " + exception.getMessage());
        }
    }
}
