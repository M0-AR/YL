package Exercise_06._03;

import java.util.Scanner;

public class PalindromeInteger { // github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        // Prompt the user to enter an integer
        System.out.print("Enter an integer: ");
        int number = input.nextInt();

        System.out.println("Is " + (isPalindrome( number )? "": "not ") + "a palindrome");
    }

    // Return the reversal of an integer, e.g., reverse(456) returns 654
    public static int reverse(int number) {
        String s = ""; // Holds reversed number
        while (number != 0) {
            s += number % 10;
            number /= 10;
        }
        number = Integer.parseInt( s );
        return number;
    }

    // Return true if number is a palindrome
    public static boolean isPalindrome(int number){
        return number == reverse( number );
    }


}
