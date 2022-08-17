package Exercise_11._12;

import java.util.ArrayList;
import java.util.Scanner;

public class SumArrayList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Double> list = new ArrayList<>();

        System.out.print("Enter 5 double numbers: ");
        for (int i = 0; i < 5; i++) {
            list.add(input.nextDouble());
        }

        System.out.println("The sum of list is: " + sum(list));
    }

    public static double sum(ArrayList<Double> list) {
        double sum = 0;
        for (Double num : list) {
           sum += num;
        }
        return sum;
    }
}
