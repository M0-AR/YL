package Exercise_28._13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Exercise_28_13 {
    private static final int SIDE = 4;
    private static final int CELLS = SIDE * SIDE;
    private static final int STATES = 1 << CELLS;
    private static final int[] MASKS = createMasks();

    public static void main(String[] args) {
        int[] parent = new int[STATES];
        int[] flipMove = new int[STATES];
        computeParents(parent, flipMove);

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the initial state (" + CELLS + " H/T characters): ");
        String pattern = input.nextLine().replaceAll("\\s+", "");
        if (pattern.length() != CELLS) {
            System.out.println("Enter exactly " + CELLS + " characters");
            return;
        }
        int state = 0;
        for (int i = 0; i < CELLS; i++) {
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
        System.out.println("The solution has " + moves.size() + " flips:");
        int board = state;
        for (int move : moves) {
            board ^= MASKS[move];
            System.out.println("Flip cell " + move + " -> " + boardText(board));
        }
    }

    private static void computeParents(int[] parent, int[] flipMove) {
        Arrays.fill(parent, -2);
        parent[0] = -1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int move = 0; move < MASKS.length; move++) {
                int next = current ^ MASKS[move];
                if (parent[next] == -2) {
                    parent[next] = current;
                    flipMove[next] = move;
                    queue.offer(next);
                }
            }
        }
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

    private static String boardText(int board) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < CELLS; i++) {
            if (i > 0 && i % SIDE == 0) {
                text.append(' ');
            }
            text.append((board & (1 << i)) != 0 ? 'H' : 'T');
        }
        return text.toString();
    }
}
