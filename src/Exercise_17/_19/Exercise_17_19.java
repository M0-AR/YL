package Exercise_17._19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Exercise_17_19 {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : promptForFile();
        Path path = Path.of(fileName);

        try {
            byte[] bytes = Files.readAllBytes(path);
            System.out.println("Hex representation of " + path + ":");
            for (int i = 0; i < bytes.length; i++) {
                System.out.printf("%4d: %s%n", i, getHex(bytes[i]));
            }
        } catch (IOException ex) {
            System.err.println("Unable to read " + path + ": " + ex.getMessage());
            System.exit(1);
        }
    }

    public static String getHex(int value) {
        return String.format("%02X", value & 0xFF);
    }

    private static String promptForFile() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a file name: ");
            return input.nextLine().trim();
        }
    }
}
