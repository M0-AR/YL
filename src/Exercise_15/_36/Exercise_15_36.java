package Exercise_15._36;

public class Exercise_15_36 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int TRIALS = 10_000;

    public static void main(String[] args) {
        for (int size = 10; size <= 80; size += 5) {
            int deadEnds = 0;
            for (int trial = 0; trial < TRIALS; trial++) {
                if (isDeadEnd(size)) {
                    deadEnds++;
                }
            }
            double probability = deadEnds * 100.0 / TRIALS;
            System.out.printf(
                    "For lattice of size %2d, the probability of dead-end paths is %.2f%%%n",
                    size, probability);
        }
    }

    private static boolean isDeadEnd(int size) {
        boolean[][] visited = new boolean[size + 1][size + 1];
        int i = size / 2;
        int j = size / 2;
        visited[i][j] = true;

        while (i > 0 && i < size && j > 0 && j < size) {
            int[] candidates = new int[4];
            int count = 0;
            for (int d = 0; d < DIRECTIONS.length; d++) {
                int nextI = i + DIRECTIONS[d][0];
                int nextJ = j + DIRECTIONS[d][1];
                if (nextI >= 0 && nextI <= size && nextJ >= 0 && nextJ <= size
                        && !visited[nextI][nextJ]) {
                    candidates[count++] = d;
                }
            }
            if (count == 0) {
                return true;
            }
            int direction = candidates[(int) (Math.random() * count)];
            i += DIRECTIONS[direction][0];
            j += DIRECTIONS[direction][1];
            visited[i][j] = true;
        }
        return false;
    }
}
