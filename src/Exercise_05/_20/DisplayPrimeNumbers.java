package Exercise_05._20;

public class DisplayPrimeNumbers {
    public static void main(String[] args) { // g
        int number = 2; // A number to be tested for primeness
        boolean isPrime = true; // If the current number is prime

        while (number <= 1000) {

            isPrime = true;

            // If the number is prime, set isPrime to false
            for (int divisor = 2; divisor <= number / 2; divisor++) {
                if (number % divisor == 0){
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) // Print the prime number
                System.out.print( number  + " ");

            number++;
        }
    }



}
