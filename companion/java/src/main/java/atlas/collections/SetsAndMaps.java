package atlas.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class SetsAndMaps {
    private SetsAndMaps() {
    }

    public static Map<String, Integer> wordCounts(List<String> words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word.toLowerCase(), 1, Integer::sum);
        }
        return counts;
    }

    public static Map<String, List<String>> byInitial(List<String> names) {
        Map<String, List<String>> groups = new TreeMap<>();
        for (String name : names) {
            if (name == null || name.isBlank()) continue;
            groups.computeIfAbsent(name.substring(0, 1).toUpperCase(), key -> new ArrayList<>())
                .add(name);
        }
        return groups;
    }

    public static List<Map.Entry<String, Integer>> top(Map<String, Integer> counts, int limit) {
        return counts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
            .limit(limit)
            .toList();
    }

    public static void main(String[] args) {
        List<String> words = List.of(
            "java", "Map", "set", "JAVA", "map", "record", "java");
        System.out.println(wordCounts(words));
        System.out.println(byInitial(List.of("Ada", "Alan", "Grace", "Barbara")));
        System.out.println(top(wordCounts(words), 2));
    }
}
