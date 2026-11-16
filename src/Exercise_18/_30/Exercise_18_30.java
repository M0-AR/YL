package Exercise_18._30;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Exercise_18_30 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise_18_30 dirName word");
            return;
        }

        Path directory = Paths.get(args[0]);
        String word = args[1];

        if (!Files.isDirectory(directory)) {
            System.out.println(directory + " is not a directory.");
            return;
        }
        if (word.isEmpty()) {
            System.out.println("The word cannot be empty.");
            return;
        }

        SearchResult result = new SearchResult();
        search(directory, word, result);

        System.out.println("Total occurrences: " + result.occurrences);
        System.out.println("Files containing the word: " + result.files);
    }

    private static void search(Path directory, String word, SearchResult result) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    search(entry, word, result);
                } else if (Files.isRegularFile(entry)) {
                    searchFile(entry, word, result);
                }
            }
        } catch (IOException ex) {
            System.out.println("Cannot read " + directory + ": " + ex.getMessage());
        }
    }

    private static void searchFile(Path file, String word, SearchResult result) {
        try {
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            int matchesInFile = 0;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                int index = line.indexOf(word);

                while (index >= 0) {
                    matchesInFile++;
                    result.occurrences++;
                    System.out.println(file + ":" + (i + 1) + ":" + (index + 1)
                            + " " + line);
                    index = line.indexOf(word, index + word.length());
                }
            }

            if (matchesInFile > 0) {
                result.files++;
            }
        } catch (IOException ex) {
            System.out.println("Skipping unreadable file " + file);
        }
    }

    private static class SearchResult {
        private int occurrences;
        private int files;
    }
}
