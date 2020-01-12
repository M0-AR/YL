package Exercise_04._08;
/*
(Find the character of an ASCII code) Write a program that receives an ASCII code
(an integer between 0 and 127) and displays its character.
*/
import java.util.Scanner;

public class FindTheCharacterOfAnASCIICode {
    public static void main(String[] args) { // check y and git
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter an ASCII code
        System.out.print("Enter an ASCII code: ");
        int code = scanner.nextInt();

        // Display character
        System.out.print("The character for ASCII code " + code + " is " + (char)code);
    }
}
