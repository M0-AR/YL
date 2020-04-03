package Exercise_06._26;

public class PalindromicPrime {
    public static void main(String[] args) {
        display();
    }

    /**
     * Display fist 100 numbers of palindromic prime numbers
     */
    public static void display() {
        int palindromicPrime = 1;
        int number = 2;
        while (palindromicPrime != 100) {

            if (palindromicNumber( number ) && isPrime( number )) {
                System.out.print( number + " " );
                palindromicPrime++;

                // Display new line
                if (palindromicPrime % 10 == 0)
                    System.out.println();
            }
            number++;
        }
    }

    /**
     * To check if the number is palindromic number
     */
    public static boolean palindromicNumber(int number) {
        String s = number + "";
        if (s.length() == 1)
            return true;

        for (int i = 0, j = s.length() - 1; i < s.length() / 2; i++, j--)
            if (s.charAt( i ) != s.charAt( j ))
                return false;

        return true;
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
