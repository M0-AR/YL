package Exercise_10._06;

import Exercise_10._05.StackOfIntegers;

import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        // Prompt the user to enter a positive integer
        Scanner scanner = new Scanner( System.in );
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();


        StackOfIntegers stackOfIntegers = new StackOfIntegers();
        // Repeatedly find prime numbers
        primeNumbersLessThanLimit(number, stackOfIntegers);

        // Print the first 30 prime numbers in decreasing order
        System.out.println("The prime numbers less than " + number + " are \n");
        final int NUMBER_PER_LINE = 10;

        while (!stackOfIntegers.empty()) {
            System.out.print( stackOfIntegers.pop() + " " );

            if (stackOfIntegers.getSize() % NUMBER_PER_LINE == 0)
                System.out.println(); // advance to the new line
        }
    }

    private static void primeNumbersLessThanLimit(int limit, StackOfIntegers stackOfIntegers) {
        for (int i = 2; i < limit; i++) {
            if (isPrime(i)) {
                stackOfIntegers.push( i );
            }
        }
    }

    private static boolean isPrime(int number) {
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0)
                return false;
        }
        return true;
    }


}
