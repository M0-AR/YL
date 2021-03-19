package Exercise_10._05;
/*********************************************************************************
 * (Displaying the prime factors) Write a program that prompts the user to enter  *
 * a positive integer and displays all its smallest factors in decreasing order.  *
 * For example, if the integer is 120, the smallest factors are displayed as      *
 * 5, 3, 2, 2, 2. Use the StackOfIntegers class to store the factors              *
 * (e.g., 2, 2, 2, 3, 5) and retrieve and display them in reverse order.          *
 *********************************************************************************/
import java.util.Scanner;

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

    // O(n^2)
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

    // O(n)
    private static void smallestFactors(int positiveInteger, StackOfIntegers stackOfIntegers) {
        int primeFactor = 2;
        while (positiveInteger/primeFactor != 1) {
            if (positiveInteger % primeFactor == 0) {
                stackOfIntegers.push( primeFactor );
                positiveInteger /= primeFactor;
            } else {
                primeFactor++;
            }
        }
        stackOfIntegers.push( positiveInteger );
    }
}
