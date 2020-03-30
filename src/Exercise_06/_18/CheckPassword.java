package Exercise_06._18;
/*
(Check password) Some websites impose certain rules for passwords. Write a
method that checks whether a string is a valid password. Suppose the password
rules are as follows:
■ A password must have at least eight characters.
■ A password consists of only letters and digits.
■ A password must contain at least two digits.
Write a program that prompts the user to enter a password and displays Valid
Password if the rules are followed or Invalid Password otherwise.
*/
import java.util.Scanner;

public class CheckPassword {
    static Scanner input = new Scanner( System.in );

    public static void main(String[] args) {

        checkPassword( ask() );
    }

    public static String ask() {
        System.out.println( "Enter a valid password" );
        String s = input.nextLine();
        return s;
    }

    public static void checkPassword(String s) {
        do {
            if (s.isEmpty())
                s = ask();

            if (s.length() < 8) {
                System.out.println( "Invalid Password, a password must have at least eight characters" );
                s = "";
            } else if (!containOnlyLettersAndDigits( s )) {
                System.out.println( "Invalid Password, a password must contain only letters and digits" );
                s = "";
            } else if (!containOverOneDigit( s ) ) {
                System.out.println( "Invalid Password, a password must contain at least two digits" );
                s = "";
            }
        } while (s.length() < 8 || !containOnlyLettersAndDigits( s ) || !containOverOneDigit( s ) );

        System.out.println( "ValidPassword" );
    }

    public static boolean containOnlyLettersAndDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit( s.charAt( i ) ))
                return false;
        }
        return true;
    }

    /** To check if the password contain more than one digit*/
    public static boolean containOverOneDigit(String s) {
        int counterForDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit( s.charAt( i ) ))
                counterForDigit++;
        }
        return counterForDigit>=2;
    }

}

class YL{
    public static void main(String[] args) {
        // Prompt the user to enter a password
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter a string for password: ");
        String s = input.nextLine();

        if (isValidPassword(s)) {
            System.out.println("Valid Password");
        }
        else {
            System.out.println("Invalid Password");
        }
    }


    /** Check if a string is a valid password */
    public static boolean isValidPassword(String s) {
        // Only letters and digits?
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i)) &&
                    !Character.isDigit(s.charAt(i)))
                return false;
        }

        // Check length
        if (s.length() < 8)
            return false;

        // Count the number of digits
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                count++;
        }

        if (count >= 2)
            return true;
        else
            return false;
    }
}
