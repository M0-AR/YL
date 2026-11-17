package Exercise_25._22;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Exercise_25_22 {
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
        List<Container> containers = firstFit(sorted);
        printContainers(containers);
        System.out.println("Total containers: " + containers.size());
        showCounterexample();
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

    private static List<Container> firstFit(List<Double> weights) {
        List<Container> containers = new ArrayList<>();
        for (double weight : weights) {
            Container destination = null;
            for (Container container : containers) {
                if (container.fits(weight)) {
                    destination = container;
                    break;
                }
            }
            if (destination == null) {
                destination = new Container();
                containers.add(destination);
            }
            destination.add(weight);
        }
        return containers;
    }

    private static void showCounterexample() {
        List<Double> weights = List.of(6.0, 5.0, 3.0, 2.0, 2.0, 2.0);
        List<Double> sorted = new ArrayList<>(weights);
        sorted.sort(Comparator.reverseOrder());
        List<Container> containers = firstFit(sorted);

        System.out.println();
        System.out.println("Example: weights 6 5 3 2 2 2");
        printContainers(containers);
        System.out.println("Largest-first uses " + containers.size()
                + " containers, but 6+2+2 and 5+3+2 use only 2.");
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

        private boolean isEmpty() {
            return weights.isEmpty();
        }

        private List<Double> getWeights() {
            return weights;
        }
    }
}
