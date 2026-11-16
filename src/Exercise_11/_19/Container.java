package Exercise_11._19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Container {
    public static final double MAX_WEIGHT = 10;

    private double totalWeight;
    private final List<Double> weights = new ArrayList<>();

    public void fillContainer(List<Double> availableWeights) {
        while (!availableWeights.isEmpty()) {
            double weight = availableWeights.get(0);
            if (totalWeight + weight > MAX_WEIGHT) {
                break;
            }

            weights.add(weight);
            availableWeights.remove(0);
            totalWeight += weight;
        }
    }

    public List<Double> getWeights() {
        return new ArrayList<>(weights);
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public static void main(String[] args) {
        Container container = new Container();
        List<Double> availableWeights =
                new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

        container.fillContainer(availableWeights);

        System.out.println("Container weights: " + container.getWeights());
        System.out.printf("Total weight: %.2f%n", container.getTotalWeight());
        System.out.println("Remaining weights: " + availableWeights);
    }
}
