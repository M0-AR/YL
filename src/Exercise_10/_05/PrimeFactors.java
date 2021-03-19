package Exercise_10._05;

import java.util.Scanner;
import java.util.Stack;
// todo: PrimeFactors and print in reverse order
public class PrimeFactors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();

        StackOfIntegers stackOfIntegers = new StackOfIntegers();
        findSmallestFactorsOfPositiveInteger(number, stackOfIntegers);

        while (!stackOfIntegers.empty())
            System.out.print(stackOfIntegers.pop() + " ");
    }

    private static void findSmallestFactorsOfPositiveInteger(int positiveInteger, StackOfIntegers stackOfIntegers) {
        int primeFactor = 2;
        while (positiveInteger != 1) {
            while (positiveInteger % primeFactor != 0) {
                primeFactor++;
            }
            positiveInteger /= primeFactor;
            stackOfIntegers.push( primeFactor );
        }
    }
}
