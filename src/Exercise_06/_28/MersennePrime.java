package Exercise_06._28;

public class MersennePrime {
    public static void main(String[] args) {
        final int LAST_DIGIT = 31;
        System.out.printf( "%-15s%s\n","p","2^p - 1" );
        System.out.println("---------------------------");
        for (int i = 2; i < LAST_DIGIT ; i++) {
            if (isPrime( i )){
                System.out.printf( "%-15d%.0f\n",i,Math.pow( 2,i )-1 );
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
}
