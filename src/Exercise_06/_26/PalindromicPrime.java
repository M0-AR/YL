package Exercise_06._26;
/*
(Palindromic prime) A palindromic prime is a prime number and also palindromic.
For example, 131 is a prime and also a palindromic prime, as are 313 and
757. Write a program that displays the first 100 palindromic prime numbers. Display
10 numbers per line, separated by exactly one space, as follows:
2 3 5 7 11 101 131 151 181 191
313 353 373 383 727 757 787 797 919 929
...
*/
public class PalindromicPrime {
    static  int NUMBERS_PER_LINE = 10;
    public static void main(String[] args) {
        display();
    }

    /**
     * Display fist 100 numbers of palindromic prime numbers
     */
    public static void display() {
        int count = 1; // Counts the number of palindromic prime numbers
        int number = 2; // Possible palindromic prime numbers
        while (count != 100) {
            if (isPalindrome( number ) && isPrime( number )) {
                System.out.print( (count % NUMBERS_PER_LINE == 0 )? number + "\n": number + " " );
                count++;
            }
            number++;
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


    /** Method 1;
     * Return true if number is palindromic
     */
    public static boolean isPalindrome(int number){
        return number == reversal(number);
    }

    /**Return the reversal of number */
    public static int reversal(int number){
        int result = 0;

        while (number != 0){
            int lastDigit = number % 10;
            result = result * 10 + lastDigit;
            number /= 10;
        }
        return result;
    }

    /** Method 2:
     * To check if the number is palindromic number
     */
//    public static boolean palindromicNumber(int number) {
//        String s = number + "";
//        if (s.length() == 1)
//            return true;
//
//        for (int i = 0, j = s.length() - 1; i < s.length() / 2; i++, j--)
//            if (s.charAt( i ) != s.charAt( j ))
//                return false;
//
//        return true;
//    }

}
