package Exercise_25._16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class Exercise_25_16 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String fileName = input.nextLine().trim();
        if (fileName.isEmpty()) {
            System.out.println("No file name was entered.");
            return;
        }

        Path path = Path.of(fileName);
        String text;
        try {
            text = Files.readString(path);
        } catch (IOException exception) {
            System.out.println("Unable to read " + path + ": " + exception.getMessage());
            return;
        }

        if (text.isEmpty()) {
            System.out.println("The file is empty.");
            return;
        }

        HuffmanTree tree = HuffmanTree.fromText(text);
        System.out.printf("%-12s %9s  %s%n", "Character", "Frequency", "Code");
        for (Map.Entry<Character, Integer> entry : tree.getFrequencies().entrySet()) {
            char symbol = entry.getKey();
            System.out.printf("%-12s %9d  %s%n",
                    describe(symbol), entry.getValue(), tree.getCodes().get(symbol));
        }
    }

    private static String describe(char symbol) {
        return switch (symbol) {
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            case ' ' -> "space";
            default -> String.valueOf(symbol);
        };
    }
}
