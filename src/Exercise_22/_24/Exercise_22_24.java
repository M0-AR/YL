package Exercise_22._24;

import java.util.Scanner;

public class Exercise_22_24 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of values: ");
        int size = input.nextInt();
        double[] values = new double[size];
        System.out.print("Enter " + size + " values: ");
        for (int i = 0; i < size; i++) {
            values[i] = input.nextDouble();
        }
        System.out.println("The smallest value is " + smallest(values, 0, size - 1));
    }

    public static double smallest(double[] values, int low, int high) {
        if (low == high) {
            return values[low];
        }
        int middle = low + (high - low) / 2;
        return Math.min(smallest(values, low, middle), smallest(values, middle + 1, high));
    }
}
