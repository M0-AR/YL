package Exercise_06._27;
/*
(Emirp) An emirp (prime spelled backward) is a nonpalindromic prime number
whose reversal is also a prime. For example, 17 is a prime and 71 is a prime, so 17
and 71 are emirps. Write a program that displays the first 100 emirps. Display 10
numbers per line, separated by exactly one space, as follows:
13 17 31 37 71 73 79 97 107 113
149 157 167 179 199 311 337 347 359 389
...
*/
public class Emirp {
    static  int NUMBERS_PER_LINE = 10;
    public static void main(String[] args) {
        display();
    }

    /**
     * Display fist 100 numbers of palindromic prime numbers
     */
    public static void display() {
        int count = 0; // Counts the number of emirps
        int number = 2;
        while (count != 100) {

            if (primeSpelledBackward( number ) && isPrime( number ) && !palindromicNumber(number)) {
                System.out.print( (count % NUMBERS_PER_LINE == 0 )? number + "\n": number + " " );
                count++;
            }
            number++;
        }
    }

    /**
     * To check if the number is spell backward
     */
    public static boolean primeSpelledBackward(int number) {

        if (number > 11){
            String numberSpelledBackward = "";
            while (number != 0){
                numberSpelledBackward += number % 10 + "";
                number /= 10;
            }

            number = Integer.parseInt( numberSpelledBackward );
            if (isPrime( number ))
                return true;
        }

        return false;
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
}
