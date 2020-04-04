package Exercise_06._29;

public class TwinPrimes {
    public static void main(String[] args) {
        final int LAST_DIGIT = 1000;
        for (int currentPrime = 3; currentPrime < LAST_DIGIT ; currentPrime++) {
            if (isPrime( currentPrime )){
                for (int nextPrime = currentPrime + 1; true ; nextPrime++) {
                    if (isPrime( nextPrime )){
                        if (nextPrime - currentPrime == 2){
                            System.out.println("("+currentPrime +", "+nextPrime+")");
                        }
                        break;
                    }
                }
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
