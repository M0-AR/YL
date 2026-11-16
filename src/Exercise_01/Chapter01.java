package Exercise_01;

import java.util.List;

public class Chapter01 {
    public static String exerciseOne() {
        return "Java " + Runtime.version().feature() + " on " + System.getProperty("os.name");
    }

    public static String exerciseTwo(String[] args) {
        if (args == null || args.length == 0) {
            return "(none)";
        }
        return args.length + ": " + String.join(", ", args);
    }

    public static List<String> exerciseThree(String sourceName) {
        String name = sourceName == null || sourceName.isBlank() ? "Program.java" : sourceName.strip();
        if (!name.endsWith(".java")) {
            name = name + ".java";
        }
        String className = name.substring(0, name.length() - 5);
        return List.of(name, className + ".class", "bytecode", "runtime");
    }

    public static void main(String[] args) {
        System.out.println(exerciseOne());
        System.out.println(exerciseTwo(args));
        System.out.println(exerciseThree("Hello.java"));
    }
}
