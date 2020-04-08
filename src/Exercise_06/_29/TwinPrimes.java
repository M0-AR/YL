package Exercise_06._29;
/*
(Twin primes) Twin primes are a pair of prime numbers that differ by 2. For example,
3 and 5 are twin primes, 5 and 7 are twin primes, and 11 and 13 are twin primes.
Write a program to find all twin primes less than 1,000. Display the output as follows:
(3, 5)
(5, 7)
...
*/
public class TwinPrimes {
    public static void main(String[] args) {
        final int LAST_DIGIT = 1000;
        for (int currentPrime = 2; currentPrime < LAST_DIGIT ; currentPrime++) {
            if (isTwinPrime( currentPrime )){
                System.out.println("("+currentPrime +", "+(currentPrime+2)+")");

            }

        }
    }

    /**
     * To check if the number is prime number
     */
    public static boolean isPrime(int number) {

        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;

        return true;
    }

    /** Return true if number and number + 2 are prime*/
    public static boolean isTwinPrime(int number ){
        return isPrime( number ) && isPrime( number+2 );
    }

}
