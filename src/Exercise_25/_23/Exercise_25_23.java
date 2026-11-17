package Exercise_25._23;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Exercise_25_23 {
    private static final double CAPACITY = 10;
    private static final double EPSILON = 1e-9;

    public static void main(String[] args) {
        List<Double> weights = readWeights();
        if (weights == null) {
            return;
        }
        if (weights.isEmpty()) {
            System.out.println("No objects to pack.");
            return;
        }

        List<Double> sorted = new ArrayList<>(weights);
        sorted.sort(Comparator.reverseOrder());
        List<Container> containers = optimalPacking(sorted);
        if (containers == null) {
            System.out.println("No packing was found.");
            return;
        }

        printContainers(containers);
        System.out.println("The optimal number of bins is " + containers.size());
        System.out.println("The search is exponential in the worst case.");
    }

    private static List<Double> readWeights() {
        Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);
        System.out.print("Enter the number of objects: ");
        if (!input.hasNextInt()) {
            System.out.println("Invalid input.");
            return null;
        }
        int count = input.nextInt();
        if (count < 0) {
            System.out.println("The number of objects cannot be negative.");
            return null;
        }
        if (count == 0) {
            return List.of();
        }

        System.out.print("Enter the weights of the objects: ");
        List<Double> weights = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (!input.hasNextDouble()) {
                System.out.println("Invalid input: expected " + count + " weights.");
                return null;
            }
            double weight = input.nextDouble();
            if (weight < 0 || weight > CAPACITY) {
                System.out.println("Each weight must be between 0 and " + format(CAPACITY) + ".");
                return null;
            }
            weights.add(weight);
        }
        return weights;
    }

    private static List<Container> optimalPacking(List<Double> weights) {
        double total = 0;
        for (double weight : weights) {
            total += weight;
        }

        int lowerBound = Math.max(1, (int) Math.ceil(total / CAPACITY - EPSILON));
        for (int limit = lowerBound; limit <= weights.size(); limit++) {
            List<Container> containers = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                containers.add(new Container());
            }
            if (place(weights, 0, containers)) {
                List<Container> result = new ArrayList<>();
                for (Container container : containers) {
                    if (!container.isEmpty()) {
                        result.add(container);
                    }
                }
                return result;
            }
        }
        return null;
    }

    private static boolean place(List<Double> weights, int index, List<Container> containers) {
        if (index == weights.size()) {
            return true;
        }

        double weight = weights.get(index);
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            if (container.fits(weight)) {
                container.add(weight);
                if (place(weights, index + 1, containers)) {
                    return true;
                }
                container.removeLast();
            }
            if (container.isEmpty()) {
                break;
            }
        }
        return false;
    }

    private static void printContainers(List<Container> containers) {
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            if (container.isEmpty()) {
                continue;
            }
            System.out.print("Container " + (i + 1) + " contains objects with weight ");
            List<Double> weights = container.getWeights();
            for (int j = 0; j < weights.size(); j++) {
                if (j > 0) {
                    System.out.print(" ");
                }
                System.out.print(format(weights.get(j)));
            }
            System.out.println();
        }
    }

    private static String format(double weight) {
        return BigDecimal.valueOf(weight).stripTrailingZeros().toPlainString();
    }

    private static final class Container {
        private final List<Double> weights = new ArrayList<>();
        private double total;

        private boolean fits(double weight) {
            return total + weight <= CAPACITY + EPSILON;
        }

        private void add(double weight) {
            weights.add(weight);
            total += weight;
        }

        private void removeLast() {
            double removed = weights.remove(weights.size() - 1);
            total -= removed;
        }

        private boolean isEmpty() {
            return weights.isEmpty();
        }

        private List<Double> getWeights() {
            return weights;
        }
    }
}
