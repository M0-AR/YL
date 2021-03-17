package Exercise_10._05;

import java.util.Scanner;
import java.util.Stack;
// todo: PrimeFactors and print in reverse order
public class PrimeFactors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        System.out.print("Enter a positive integer: ");
        int userInput = scanner.nextInt();

        int[] smallestFactors = findSmallestFactorsOfPositiveInteger(userInput);
    }

    private static int[] findSmallestFactorsOfPositiveInteger(int positiveInteger) {
        Stack<Integer> smallestFactors = new Stack<>();
        int diviser = 2;
        while (positiveInteger != 1) {
            while (positiveInteger % diviser != 0) {
                diviser++;
            }
            positiveInteger /= diviser;
            smallestFactors.add( diviser );
        }
        System.out.println(smallestFactors);
        return new int[0];
    }
}
