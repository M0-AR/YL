package Exercise_18._31;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exercise_18_31 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Exercise_18_31 dirName oldWord newWord");
            return;
        }

        Path directory = Paths.get(args[0]);
        String oldWord = args[1];
        String newWord = args[2];

        if (!Files.isDirectory(directory)) {
            System.out.println(directory + " is not a directory.");
            return;
        }
        if (oldWord.isEmpty()) {
            System.out.println("The old word cannot be empty.");
            return;
        }
        if (oldWord.equals(newWord)) {
            System.out.println("The old and new words are identical.");
            return;
        }

        int[] totals = new int[2];
        replace(directory, oldWord, newWord, totals);

        System.out.println("Files changed: " + totals[0]);
        System.out.println("Replacements: " + totals[1]);
    }

    private static void replace(Path directory, String oldWord, String newWord,
                                int[] totals) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    replace(entry, oldWord, newWord, totals);
                } else if (Files.isRegularFile(entry)) {
                    replaceFile(entry, oldWord, newWord, totals);
                }
            }
        } catch (IOException ex) {
            System.out.println("Cannot read " + directory + ": " + ex.getMessage());
        }
    }

    private static void replaceFile(Path file, String oldWord, String newWord,
                                    int[] totals) {
        try {
            String text = Files.readString(file, StandardCharsets.UTF_8);
            int matches = count(text, oldWord);

            if (matches == 0) {
                return;
            }

            Files.writeString(file, text.replace(oldWord, newWord),
                    StandardCharsets.UTF_8);
            totals[0]++;
            totals[1] += matches;
            System.out.println("Updated " + file + " (" + matches + ")");
        } catch (IOException ex) {
            System.out.println("Skipping unreadable file " + file);
        }
    }

    private static int count(String text, String word) {
        int count = 0;
        int index = text.indexOf(word);

        while (index >= 0) {
            count++;
            index = text.indexOf(word, index + word.length());
        }

        return count;
    }
}
