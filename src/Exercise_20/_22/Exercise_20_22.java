package Exercise_20._22;

import java.util.ArrayDeque;
import java.util.Deque;

public class Exercise_20_22 {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        StringBuilder moves = new StringBuilder();
        int count = moveDisks(n, 'A', 'B', 'C', moves);
        System.out.println("Number of moves: " + count);
        System.out.print(moves);
    }

    public static int moveDisks(int n, char from, char aux, char to, StringBuilder moves) {
        Deque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame(n, from, aux, to, 0));
        int count = 0;
        while (!stack.isEmpty()) {
            Frame frame = stack.pop();
            if (frame.n == 0) {
                continue;
            }
            if (frame.stage == 0) {
                frame.stage = 1;
                stack.push(frame);
                stack.push(new Frame(frame.n - 1, frame.from, frame.to, frame.aux, 0));
            } else if (frame.stage == 1) {
                moves.append("Move disk ").append(frame.n)
                        .append(" from ").append(frame.from)
                        .append(" to ").append(frame.to).append('\n');
                count++;
                frame.stage = 2;
                stack.push(frame);
                stack.push(new Frame(frame.n - 1, frame.aux, frame.from, frame.to, 0));
            }
        }
        return count;
    }

    private static class Frame {
        int n;
        char from;
        char aux;
        char to;
        int stage;

        Frame(int n, char from, char aux, char to, int stage) {
            this.n = n;
            this.from = from;
            this.aux = aux;
            this.to = to;
            this.stage = stage;
        }
    }
}
