package Exercise_27;

import java.util.Map;
import java.util.TreeMap;

public class Chapter27 {
    public static Map<String, Integer> exerciseOne(String[] words) {
        Map<String, Integer> counts = new TreeMap<>();
        if (words != null) {
            for (String word : words) {
                if (word != null) {
                    counts.merge(word, 1, Integer::sum);
                }
            }
        }
        return counts;
    }

    public static int exerciseTwo(String key, int bucketCount) {
        if (key == null || bucketCount <= 0) {
            return -1;
        }
        return Math.floorMod(key.hashCode(), bucketCount);
    }

    public static int exerciseThree(int entries, int buckets, double loadFactor) {
        if (buckets <= 0) {
            return 1;
        }
        if (loadFactor <= 0 || entries < 0) {
            return buckets;
        }
        int next = buckets;
        while (entries > next * loadFactor) {
            next *= 2;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(exerciseOne(new String[] {"red", "blue", "red", "green", "red"}));
        System.out.println(exerciseTwo("atlas", 16));
        System.out.println(exerciseThree(14, 8, 0.75));
    }
}
