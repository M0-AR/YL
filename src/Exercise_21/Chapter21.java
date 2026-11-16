package Exercise_21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Chapter21 {
    public static List<String> exerciseOne(String text) {
        return new ArrayList<>(new TreeSet<>(tokens(text)));
    }

    public static Map<String, Integer> exerciseTwo(String text) {
        Map<String, Integer> counts = new TreeMap<>();
        for (String token : tokens(text)) {
            counts.merge(token, 1, Integer::sum);
        }
        return counts;
    }

    public static List<String> exerciseThree(Set<String> left, Set<String> right) {
        Set<String> shared = new TreeSet<>(left == null ? Set.of() : left);
        if (right != null) {
            shared.retainAll(right);
        } else {
            shared.clear();
        }
        return new ArrayList<>(shared);
    }

    private static List<String> tokens(String text) {
        List<String> words = new ArrayList<>();
        if (text == null) {
            return words;
        }
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9']+", " ").strip();
        if (cleaned.isEmpty()) {
            return words;
        }
        for (String word : cleaned.split("\\s+")) {
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
        return words;
    }

    public static void main(String[] args) {
        String sample = "The quick fox, the slow fox!";
        System.out.println(exerciseOne(sample));
        System.out.println(exerciseTwo(sample));
        System.out.println(exerciseThree(Set.of("fox", "quick", "den"), Set.of("slow", "fox")));
    }
}
