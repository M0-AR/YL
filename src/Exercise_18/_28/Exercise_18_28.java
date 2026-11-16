package Exercise_18._28;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Exercise_18_28 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a directory or a file: ");
        Path path = Paths.get(input.nextLine());

        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println(path + " does not exist.");
            return;
        }

        try {
            System.out.println(getSize(path) + " bytes");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static long getSize(Path path) throws IOException {
        long total = 0;
        Deque<Path> stack = new ArrayDeque<>();
        stack.push(path);

        while (!stack.isEmpty()) {
            Path current = stack.pop();

            if (Files.isRegularFile(current, LinkOption.NOFOLLOW_LINKS)) {
                total += Files.size(current);
            } else if (Files.isDirectory(current, LinkOption.NOFOLLOW_LINKS)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(current)) {
                    for (Path child : stream) {
                        stack.push(child);
                    }
                }
            }
        }

        return total;
    }
}
