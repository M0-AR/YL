package Exercise_17._16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Exercise_17_16 {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : promptForFile();
        Path path = Path.of(fileName);

        try {
            byte[] bytes = Files.readAllBytes(path);
            long[] frequencies = new long[256];
            for (byte value : bytes) {
                frequencies[value & 0xFF]++;
            }

            System.out.println("Character frequencies in " + path + ":");
            for (int value = 0; value < frequencies.length; value++) {
                if (frequencies[value] > 0) {
                    System.out.printf("%-8s %,d%n", label(value), frequencies[value]);
                }
            }
            System.out.printf("Total characters: %,d%n", bytes.length);
        } catch (IOException ex) {
            System.err.println("Unable to read " + path + ": " + ex.getMessage());
            System.exit(1);
        }
    }

    private static String promptForFile() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the name of an ASCII text file: ");
            return input.nextLine().trim();
        }
    }

    private static String label(int value) {
        return switch (value) {
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            case ' ' -> "' '";
            default -> value >= 32 && value < 127
                    ? "'" + (char) value + "'"
                    : String.format("0x%02X", value);
        };
    }
}
