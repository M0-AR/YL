package Exercise_06._18;

import java.util.Scanner;

public class CheckPassword { // github and y
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
            } else if (containOverOneDigit( s ) < 2) {
                System.out.println( "Invalid Password, a password must contain at least two digits" );
                s = "";
            }
        } while (s.length() < 8 || !containOnlyLettersAndDigits( s ) || containOverOneDigit( s ) < 2);

        System.out.println( "ValidPassword" );
    }

    public static boolean containOnlyLettersAndDigits(String s) {
        int counterForLetters = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt( i ) >= 97 && s.charAt( i ) <= 122)
                counterForLetters++;
        }

        return (s.length() == containOverOneDigit( s ) + counterForLetters);
    }

    public static int containOverOneDigit(String s) {
        int counterForDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt( i ) >= 48 && s.charAt( i ) <= 57)
                counterForDigit++;
        }

        return counterForDigit;
    }

}
