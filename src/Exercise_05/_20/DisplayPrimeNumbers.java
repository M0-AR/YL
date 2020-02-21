package Exercise_05._20;
/*
(Display prime numbers between 2 and 1,000) Modify Listing 5.15 to display all
the prime numbers between 2 and 1,000, inclusive. Display eight prime numbers
per line. Numbers are separated by exactly one space.
*/
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
