package Exercise_04._09;

import java.util.Scanner;

public class FindTheUnicodeOfACharacter {
    public static void main(String[] args) { // check git
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter an ASCII code
        System.out.print("Enter an ASCII code: ");
        char code = scanner.next().charAt( 0 );


        // Display character
        System.out.println("The character for ASCII code " + code + " is " + (int)code);
    }
}
