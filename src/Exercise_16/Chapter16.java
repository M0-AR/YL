package Exercise_16;

public class Chapter16 {
    public static int exerciseOne(int row, int column, int columns) {
        if (row < 0 || column < 0 || columns <= 0 || column >= columns) {
            return -1;
        }
        return row * columns + column;
    }

    public static double exerciseTwo(double size, double factor) {
        if (size < 0 || factor < 0) {
            return 0.0;
        }
        return round(size * factor, 2);
    }

    public static String exerciseThree(int totalSeconds) {
        int safe = Math.max(totalSeconds, 0);
        int hours = safe / 3600;
        int minutes = (safe % 3600) / 60;
        int seconds = safe % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static double round(double value, int places) {
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }

    public static void main(String[] args) {
        System.out.println(exerciseOne(3, 4, 8));
        System.out.println(exerciseTwo(120.0, 1.5));
        System.out.println(exerciseThree(3725));
    }
}
