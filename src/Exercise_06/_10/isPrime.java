package Exercise_06._10;
/*
(Use the isPrime Method) Listing 6.7, PrimeNumberMethod.java, provides the
isPrime(int number) method for testing whether a number is prime. Use this
method to find the number of prime numbers less than 10000.
*/
public class isPrime {
    public static void main(String[] args) {
        int count = 0;
        for (int number = 0; number < 10000; number++)
            if (isPrime( number ))
                count++;

        System.out.println("The number of prime number < 10000 is "
            + count);
    }

    /** To check if the number is prime or not */
    public static boolean isPrime(int n){
        for (int divisor = 2; divisor < n / 2 ; divisor++)
            if (n % divisor == 0) // If true, number is not prime
                return false; // Number is not prime
        return true;// Number is prime
    }
}
