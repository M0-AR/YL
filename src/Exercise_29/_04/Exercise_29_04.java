package Exercise_29._04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Exercise_29_04 {
    private static final int SIDE = 3;
    private static final int CELLS = SIDE * SIDE;
    private static final int STATES = 1 << CELLS;
    private static final int WEIGHT = 3;
    private static final int[] MASKS = createMasks();

    public static void main(String[] args) {
        double[] cost = new double[STATES];
        int[] parent = new int[STATES];
        int[] flipMove = new int[STATES];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -2);
        cost[0] = 0;

        record Node(double cost, int state) {
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));
        queue.offer(new Node(0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.cost > cost[node.state]) {
                continue;
            }
            for (int move = 0; move < MASKS.length; move++) {
                int next = node.state ^ MASKS[move];
                double nextCost = node.cost + WEIGHT;
                if (nextCost < cost[next]) {
                    cost[next] = nextCost;
                    parent[next] = node.state;
                    flipMove[next] = move;
                    queue.offer(new Node(nextCost, next));
                }
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the initial state (" + CELLS + " H/T characters): ");
        String pattern = input.nextLine().replaceAll("\\s+", "");
        int state = 0;
        for (int i = 0; i < Math.min(CELLS, pattern.length()); i++) {
            if (pattern.charAt(i) == 'H' || pattern.charAt(i) == 'h') {
                state |= 1 << i;
            }
        }
        if (parent[state] == -2) {
            System.out.println("No solution exists for this pattern");
            return;
        }
        List<Integer> moves = new ArrayList<>();
        for (int current = state; current != 0; current = parent[current]) {
            moves.add(flipMove[current]);
        }
        System.out.println("Number of flips: " + moves.size());
        System.out.println("Total weight: " + cost[state]);
        System.out.println("Flips: " + moves);
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
