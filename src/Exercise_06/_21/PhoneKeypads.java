package Exercise_06._21;
/*
(Phone keypads) The international standard letter/number mapping for telephones
is shown in Programming Exercise 4.15. Write a method that returns a number,
given an uppercase letter, as follows:
int getNumber(char uppercaseLetter)
Write a test program that prompts the user to enter a phone number as a string.
The input number may contain letters. The program translates a letter (uppercase
or lowercase) to a digit and leaves all other characters intact.
*/
import java.util.Scanner;

public class PhoneKeypads {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a phone number as a string.
        System.out.print( "Enter a string: " );
        String s = input.nextLine();

        //
        for (int i = 0; i < s.length() ; i++) {
            if (Character.isLetter( s.charAt( i ) )){
                System.out.print(getNumber( Character.toUpperCase( s.charAt( i ) ) ));
            }else {
                System.out.print(s.charAt( i ));
            }
        }
    }

    public static int getNumber(char uppercaseLetter){
        if (uppercaseLetter <= 'C' ){
            return 2;
        }else if (uppercaseLetter <= 'F'){
            return 3;
        }else if (uppercaseLetter <= 'I'){
            return 4;
        }else if (uppercaseLetter <= 'L'){
            return 5;
        }else if (uppercaseLetter <= 'O'){
            return 6;
        }else if (uppercaseLetter <= 'S'){
            return 7;
        }else if (uppercaseLetter <= 'V'){
            return 8;
        }else {
            return 9;
        }
    }
}
