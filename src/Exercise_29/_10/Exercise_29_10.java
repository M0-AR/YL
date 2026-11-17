package Exercise_29._10;

import java.io.IOException;
import java.io.PrintWriter;

public class Exercise_29_10 {
    public static void main(String[] args) throws IOException {
        String[] lines = {
            "12",
            "0, 1, 807 | 0, 3, 1331 | 0, 5, 2097",
            "1, 2, 381 | 1, 3, 1267",
            "2, 3, 1015 | 2, 4, 1663 | 2, 10, 1435",
            "3, 4, 599 | 3, 5, 1003",
            "4, 5, 533 | 4, 7, 1260 | 4, 8, 864 | 4, 10, 496",
            "5, 6, 983 | 5, 7, 787",
            "6, 7, 214",
            "7, 8, 888",
            "8, 9, 661 | 8, 10, 781 | 8, 11, 810",
            "9, 11, 1187",
            "10, 11, 239"
        };
        try (PrintWriter output = new PrintWriter("WeightedGraph1.txt")) {
            for (String line : lines) {
                output.println(line);
            }
        }
        System.out.println("Created WeightedGraph1.txt with " + lines.length + " lines");
    }
}
