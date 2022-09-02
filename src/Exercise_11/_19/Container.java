package Exercise_11._19;

import java.util.ArrayList;

public class Container {
    public final int MAX_WEIGHT = 0;
    public static int numberOfObjects = 0;
    private double totalWeight = 0;
    private ArrayList<Double> weights = new ArrayList<>();



    public ArrayList<Double> getWeights() {
        return new ArrayList<>(weights);
    }
}
