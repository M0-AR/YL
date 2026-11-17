package Exercise_20._20;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_20_20 {
    public static void main(String[] args) {
        File directory = new File(args.length > 0 ? args[0] : ".");
        System.out.println(directory.getAbsolutePath() + " size: " + getSize(directory) + " bytes");
    }

    public static long getSize(File directory) {
        Deque<File> stack = new ArrayDeque<>();
        stack.push(directory);
        long size = 0;
        while (!stack.isEmpty()) {
            File current = stack.pop();
            if (current.isFile()) {
                size += current.length();
            } else {
                File[] files = current.listFiles();
                if (files == null) {
                    continue;
                }
                for (File file : files) {
                    stack.push(file);
                }
            }
        }
        return size;
    }
}
