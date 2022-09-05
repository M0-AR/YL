package Exercise_11._19;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainerTest {
    List<Container> containers;
    List<Double> weights;
    List<List<Double>> expectedWeightsOrder;

    @Before
    public void setUp() throws Exception {
        containers = new ArrayList<>();
        weights = new ArrayList<>(Arrays.asList(7.0, 5.0, 2.0, 3.0, 5.0, 8.0));

        for (int i = 0; i < 4; i++) {
           containers.add(new Container());
           containers.get(i).fillContainer(weights);
        }

        expectedWeightsOrder = new ArrayList<>();
        // Weights expected in first container
        expectedWeightsOrder.add(new ArrayList<>(Arrays.asList(7.0, 2.0)));
        // Weights expected in second container
        expectedWeightsOrder.add(new ArrayList<>(Arrays.asList(5.0, 3.0)));
        // Weights expected in third container
        expectedWeightsOrder.add(new ArrayList<>(Arrays.asList(5.0)));
        // Weights expected in forth container
        expectedWeightsOrder.add(new ArrayList<>(Arrays.asList(8.0)));
    }


    @Test
    public void getWeights() {
        List<List<Double>> weights = new ArrayList<>();

        for (int i = 0; i < this.containers.size(); i++) {
           weights.add(this.containers.get(i).getWeights());
        }

        Assert.assertArrayEquals(this.expectedWeightsOrder.toArray(), weights.toArray());
    }

}