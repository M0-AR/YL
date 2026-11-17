package Exercise_29._06;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Exercise_29_06 {
    public static void main(String[] args) throws IOException {
        WeightedTailModel16 model = new WeightedTailModel16();
        int solvable = 0;
        for (int state = 0; state < WeightedTailModel16.STATES; state++) {
            if (model.isSolvable(state)) {
                solvable++;
            }
        }
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("WeightedTailModel16.dat"))) {
            output.writeObject(model);
        }
        System.out.println("Solvable patterns: " + solvable
                + " of " + WeightedTailModel16.STATES);
        System.out.println("Saved the model to WeightedTailModel16.dat");
    }
}
