package Exercise_29._05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Exercise_29_05 {
    private static final int SIDE = 3;
    private static final int CELLS = SIDE * SIDE;
    private static final int STATES = 1 << CELLS;

    public static void main(String[] args) {
        int[] masks = createMasks();
        int[] depth = new int[STATES];
        Arrays.fill(depth, -1);
        depth[0] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int state = queue.poll();
            for (int mask : masks) {
                int next = state ^ mask;
                if (depth[next] == -1) {
                    depth[next] = depth[state] + 1;
                    queue.offer(next);
                }
            }
        }

        double[] cost = new double[STATES];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[0] = 0;
        record Node(double cost, int state) {
        }
        PriorityQueue<Node> weighted = new PriorityQueue<>(
                (a, b) -> Double.compare(a.cost, b.cost));
        weighted.offer(new Node(0, 0));
        while (!weighted.isEmpty()) {
            Node node = weighted.poll();
            if (node.cost > cost[node.state]) {
                continue;
            }
            for (int mask : masks) {
                int next = node.state ^ mask;
                if (node.cost + 1 < cost[next]) {
                    cost[next] = node.cost + 1;
                    weighted.offer(new Node(cost[next], next));
                }
            }
        }

        int compared = 0;
        int mismatches = 0;
        for (int state = 0; state < STATES; state++) {
            if (depth[state] == -1 && cost[state] == Double.POSITIVE_INFINITY) {
                continue;
            }
            compared++;
            if (depth[state] != cost[state]) {
                mismatches++;
            }
        }
        System.out.println("Reachable patterns compared: " + compared);
        System.out.println("Patterns with different shortest path lengths: " + mismatches);
        System.out.println(mismatches == 0
                ? "The conjecture is proved: both models give the same shortest path length."
                : "The conjecture is disproved.");
    }

    private static int[] createMasks() {
        int[] masks = new int[CELLS];
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
            masks[i] = mask;
        }
        return masks;
    }
}
