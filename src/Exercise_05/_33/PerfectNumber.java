package Exercise_05._33;

/*
(Perfect number) A positive integer is called a perfect number if it is equal to
the sum of all of its positive divisors, excluding itself. For example, 6 is the
first perfect number because 6 = 3 + 2 + 1. The next is 28 = 14 + 7 + 4 + 2 + 1.
There are four perfect numbers less than 10,000. Write a program to find all
the sum of all of its positive divisors, excluding itself. For example, 6 is the first
perfect number because 6 = 3 + 2 + 1. The next is 28 = 14 + 7 + 4 + 2
+ 1. There are four perfect numbers less than 10,000. Write a program to find all
these four numbers.
*/
public class PerfectNumber {

    public static void main(String[] args) {
        System.out.println( "The four perfect number less than 10,000:" );

        int sum;    // Holds the sum of the positive divisors
        // Find all perfect numbers less than 10,000
        for (int i = 1; i < 10000; i++) {
            sum = 0;    // Reset Accumulator to 0

            for (int k = 1; k < i; k++) {
                // Test for divisor
                if (i % k == 0)
                    sum += k;
            }
            // Test if sum of all positive divisors are equal to number
            if (i == sum)
                System.out.printf( "%d\n", i );

        }

    }

    /*public static void main(String[] args) { // github
        int count = 6; // To stop while loop
        int number = 6;// To check if the number is (Perfect Number)
        int positiveInteger = 0; // Is the perfect number

        while (count < 10000) {
            number = count;
            positiveInteger = 0;

            while (number % 2 == 0) {
                number /= 2;

                if (number % 2 != 0) {

                    while (number != 0)
                        positiveInteger += number--;

                    if (positiveInteger == count)
                        System.out.println( positiveInteger );

                    number++; //
                }
            }
            count++;
        }

    }*/


}
