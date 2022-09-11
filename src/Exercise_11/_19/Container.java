package Exercise_11._19;

import java.util.ArrayList;
import java.util.List;

// TODO
public class Container {
    public final int MAX_WEIGHT = 10;
//    public static int numberOfObjects = 0;
    private double totalWeight = 0;
    private List<Double> weights = new ArrayList<>();

    public void fillContainer(List<Double> weights) {
        Double weight = weights.get(0);

        while(totalWeight + weight <= MAX_WEIGHT) {

            this.weights.add(weight);
            weights.remove(weight);
            totalWeight += weight;

            if (weights.isEmpty())
                break;

            weight = weights.get(0);
        }
    }

    public List<Double> getWeights() {
        return new ArrayList<>(weights);
    }
}
