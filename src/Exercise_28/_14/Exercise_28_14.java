package Exercise_28._14;

import java.util.LinkedList;

public class Exercise_28_14 {
    private static final int SIDE = 4;
    private static final int CELLS = SIDE * SIDE;
    private static final int STATES = 1 << CELLS;

    public static void main(String[] args) {
        int[] masks = createMasks();
        boolean[] reachable = new boolean[STATES];
        reachable[0] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int count = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int mask : masks) {
                int next = current ^ mask;
                if (!reachable[next]) {
                    reachable[next] = true;
                    count++;
                    queue.offer(next);
                }
            }
        }
        System.out.println("Starting patterns: " + STATES);
        System.out.println("Patterns with a solution: " + count);
        System.out.println("Patterns without a solution: " + (STATES - count));
    }

    private static int[] createMasks() {
        int[] result = new int[CELLS];
        for (int i = 0; i < CELLS; i++) {
            int row = i / SIDE;
            int column = i % SIDE;
            int mask = 1 << i;
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (Math.abs(dr) + Math.abs(dc) != 1) {
                        continue;
                    }
                    int r = row + dr;
                    int c = column + dc;
                    if (r >= 0 && r < SIDE && c >= 0 && c < SIDE) {
                        mask |= 1 << (r * SIDE + c);
                    }
                }
            }
            result[i] = mask;
        }
        return result;
    }
}
