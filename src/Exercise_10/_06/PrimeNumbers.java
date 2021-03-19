package Exercise_10._06;

import Exercise_10._05.StackOfIntegers;

import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();

        StackOfIntegers stackOfIntegers = new StackOfIntegers(  );
        primeNumbersLessThanNumber(number, stackOfIntegers);

        while (!stackOfIntegers.empty())
            System.out.print(stackOfIntegers.pop() + " ");
    }

    private static void primeNumbersLessThanNumber(int number, StackOfIntegers stackOfIntegers) {
        for (int i = 2; i < number; i++) {
            if (isPrime(i)) {
                stackOfIntegers.push( i );
            }
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i < Math.sqrt( number ); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }


}
