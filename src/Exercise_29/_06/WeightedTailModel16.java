package Exercise_29._06;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedTailModel16 implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SIDE = 4;
    public static final int CELLS = SIDE * SIDE;
    public static final int STATES = 1 << CELLS;
    private final int[] parent;
    private final int[] flipMove;
    private final double[] cost;

    public WeightedTailModel16() {
        parent = new int[STATES];
        flipMove = new int[STATES];
        cost = new double[STATES];
        Arrays.fill(parent, -2);
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        int[] masks = createMasks();
        parent[0] = -1;
        cost[0] = 0;

        class Entry {
            final double cost;
            final int state;

            Entry(double cost, int state) {
                this.cost = cost;
                this.state = state;
            }
        }
        PriorityQueue<Entry> queue = new PriorityQueue<>((first, second) ->
                Double.compare(first.cost, second.cost));
        queue.offer(new Entry(0, 0));
        while (!queue.isEmpty()) {
            Entry entry = queue.poll();
            if (entry.cost > cost[entry.state]) {
                continue;
            }
            for (int move = 0; move < masks.length; move++) {
                int next = entry.state ^ masks[move];
                double nextCost = entry.cost + 1;
                if (nextCost < cost[next]) {
                    cost[next] = nextCost;
                    parent[next] = entry.state;
                    flipMove[next] = move;
                    queue.offer(new Entry(nextCost, next));
                }
            }
        }
    }

    public boolean isSolvable(int state) {
        return parent[state] != -2;
    }

    public double getCost(int state) {
        return cost[state];
    }

    public List<Integer> getSolution(int state) {
        if (!isSolvable(state)) {
            return null;
        }
        List<Integer> moves = new ArrayList<>();
        for (int current = state; current != 0; current = parent[current]) {
            moves.add(flipMove[current]);
        }
        return moves;
    }

    public static int[] createMasks() {
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
