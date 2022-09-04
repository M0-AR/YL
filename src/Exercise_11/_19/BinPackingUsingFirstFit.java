package Exercise_11._19;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BinPackingUsingFirstFit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);

        ArrayList<Container> containers = new ArrayList<>();
        ArrayList<Double> weights = new ArrayList<>();

        System.out.print("Enter the number of objects: ");
        int numberOfObject = input.nextInt();

        for (int i = 1; i < numberOfObject + 1; i++) {
            System.out.print("Enter the weight of object " + i + ": ");
            double weight = input.nextDouble();
            weights.add(weight);
        }

    }
}
