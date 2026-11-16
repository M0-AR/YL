package Exercise_15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Chapter15 {
    public static List<String> exerciseOne(List<String> events) {
        Deque<String> queue = new ArrayDeque<>(events == null ? List.of() : events);
        List<String> processed = new ArrayList<>();
        while (!queue.isEmpty()) {
            processed.add(queue.pollFirst());
        }
        return processed;
    }

    public static double exerciseTwo(int frameIndex, int framesPerSecond) {
        if (frameIndex < 0 || framesPerSecond <= 0) {
            return 0.0;
        }
        return round(frameIndex / (double) framesPerSecond, 3);
    }

    public static double exerciseThree(int clicks, int elapsedSeconds) {
        if (clicks < 0 || elapsedSeconds <= 0) {
            return 0.0;
        }
        return round(clicks / (double) elapsedSeconds, 2);
    }

    private static double round(double value, int places) {
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }

    public static void main(String[] args) {
        System.out.println(exerciseOne(List.of("start", "scroll", "resize", "close")));
        System.out.println(exerciseTwo(45, 30));
        System.out.println(exerciseThree(37, 12));
    }
}
