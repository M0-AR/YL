package Exercise_18._29;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Exercise_18_29 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a directory: ");
        Path directory = Paths.get(input.nextLine());

        if (!Files.isDirectory(directory)) {
            System.out.println(directory + " is not a directory.");
            return;
        }

        int fileCount = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    fileCount++;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }

        System.out.println("There are " + fileCount + " files in " + directory);
    }
}
