package Exercise_06._28;
/*
(Mersenne prime) A prime number is called a Mersenne prime if it can be written
in the form 2p - 1 for some positive integer p. Write a program that finds all
Mersenne primes with p <= 31 and displays the output as follows:
p             2^p –1
-----------------------
2             3
3             7
5             31
...
*/
public class MersennePrime {
    public static void main(String[] args) {
        final int LAST_DIGIT = 31;
        System.out.printf( "%-15s%s\n", "p", "2^p - 1" );
        System.out.println( "---------------------------" );
        for (int i = 2; i < LAST_DIGIT; i++) {
            if (isPrime( i )) {
                System.out.printf( "%-15d%d\n", i, mersennePrime(i));
            }
        }
    }

    /**
     * To check if the number is prime number
     */
    public static boolean isPrime(int number) {

        if (number == 1 || number == 2)
            return true;


        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;

        return true;
    }


    /**
     * Return the mersenne prime of number
     */
    public static int mersennePrime(int number) {
        return (int) (Math.pow( 2, number ) - 1);
    }
}

