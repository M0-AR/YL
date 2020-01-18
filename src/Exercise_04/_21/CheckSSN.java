package Exercise_04._21;
/*
(Check SSN) Write a program that prompts the user to enter a Social Security
number in the format DDD-DD-DDDD, where D is a digit. Your program should
check whether the input is valid.
*/
import java.util.Scanner;

public class CheckSSN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter
        System.out.print( "Enter a SSN: " );
        String ssn = scanner.nextLine();

        // Display result
        boolean isValid = (ssn.length() == 11)
                && (Character.isDigit( 0 ))
                && (Character.isDigit( 1 ))
                && (Character.isDigit( 2 ))
                && ssn.charAt( 3 ) == '-'
                && (Character.isDigit( 4 ))
                && (Character.isDigit( 5 ))
                && ssn.charAt( 6 ) == '-'
                && (Character.isDigit( 7 )) && (Character.isDigit( 8 ))
                && (Character.isDigit( 9 )) && (Character.isDigit( 10 ));

        System.out.println(ssn + " is " + ((isValid) ? "a valid " : "an invalid ") + "social security number");
    }
}
