package Exercise_20._19;

import java.util.ArrayList;
import java.util.List;

public class Exercise_20_19 {
    private static final int RANKS = 13;
    private static final int SUITS = 4;

    public static void main(String[] args) {
        long total = combinations(52, 4);
        long solvable = 0;
        int[] picks = new int[4];
        for (int a = 1; a <= RANKS; a++) {
            picks[0] = a;
            for (int b = a; b <= RANKS; b++) {
                picks[1] = b;
                for (int c = b; c <= RANKS; c++) {
                    picks[2] = c;
                    for (int d = c; d <= RANKS; d++) {
                        picks[3] = d;
                        if (solve(picks.clone()) != null) {
                            solvable += multiplicity(picks);
                        }
                    }
                }
            }
        }
        System.out.println("All possible picks: " + total);
        System.out.println("Picks with a 24-point solution: " + solvable);
        System.out.printf("Success ratio: %.4f%%%n", 100.0 * solvable / total);
    }

    private static long multiplicity(int[] picks) {
        long result = 1;
        int index = 0;
        while (index < picks.length) {
            int value = picks[index];
            int count = 0;
            while (index < picks.length && picks[index] == value) {
                count++;
                index++;
            }
            result *= combinations(SUITS, count);
        }
        return result;
    }

    private static long combinations(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }

    public static String solve(int[] numbers) {
        List<Expression> values = new ArrayList<>();
        for (int number : numbers) {
            values.add(new Expression(String.valueOf(number), number));
        }
        return solve(values);
    }

    private static String solve(List<Expression> values) {
        if (values.size() == 1) {
            return Math.abs(values.get(0).value - 24) < 1e-6 ? values.get(0).text : null;
        }
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                Expression first = values.get(i);
                Expression second = values.get(j);
                List<Expression> next = new ArrayList<>();
                for (int k = 0; k < values.size(); k++) {
                    if (k != i && k != j) {
                        next.add(values.get(k));
                    }
                }
                for (Expression combined : combine(first, second)) {
                    next.add(combined);
                    String result = solve(next);
                    if (result != null) {
                        return result;
                    }
                    next.remove(next.size() - 1);
                }
            }
        }
        return null;
    }

    private static List<Expression> combine(Expression first, Expression second) {
        List<Expression> results = new ArrayList<>();
        results.add(new Expression("(" + first.text + " + " + second.text + ")",
                first.value + second.value));
        results.add(new Expression("(" + first.text + " - " + second.text + ")",
                first.value - second.value));
        results.add(new Expression("(" + second.text + " - " + first.text + ")",
                second.value - first.value));
        results.add(new Expression("(" + first.text + " * " + second.text + ")",
                first.value * second.value));
        if (Math.abs(second.value) > 1e-9) {
            results.add(new Expression("(" + first.text + " / " + second.text + ")",
                    first.value / second.value));
        }
        if (Math.abs(first.value) > 1e-9) {
            results.add(new Expression("(" + second.text + " / " + first.text + ")",
                    second.value / first.value));
        }
        return results;
    }

    private static class Expression {
        final String text;
        final double value;

        Expression(String text, double value) {
            this.text = text;
            this.value = value;
        }
    }
}
