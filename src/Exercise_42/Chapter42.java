package Exercise_42;

public class Chapter42 {
    public static int clamp(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must not exceed max");
        }
        return Math.max(min, Math.min(max, value));
    }

    public static double average(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("at least one value is required");
        }
        long total = 0L;
        for (int value : values) {
            total += value;
        }
        return (double) total / values.length;
    }

    public static int parseOrDefault(String text, int fallback) {
        if (text == null || text.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException error) {
            return fallback;
        }
    }

    public static void main(String[] args) {
        System.out.println(clamp(12, 0, 10));
        System.out.println(average(new int[] {3, 7, 11}));
        System.out.println(parseOrDefault(" 42 ", -1));
        System.out.println(parseOrDefault("unknown", -1));
    }
}
