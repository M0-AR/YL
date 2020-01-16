package Exercise_04._15;
/*
(Phone key pads) The international standard letter/number mapping found on the
telephone is shown below:
Write a program that prompts the user to enter a letter and displays its corresponding
number.
*/
import java.util.Scanner;

public class PhoneKeyPads {
    public static void main(String[] args) { //done
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a letter
        System.out.print("Enter a letter: ");
        char letter = scanner.next().charAt( 0 );

        // Display its corresponding number
        int number = 0;
        if(Character.isLetter( letter )){
            if (letter >= 'w')
                number = 9;
            else if (letter >= 'T')
                number = 8;
            else if (letter >= 'P')
                number = 7;
            else if (letter >= 'M')
                number = 6;
            else if (letter >= 'J')
                number = 5;
            else if (letter >= 'G')
                number = 4;
            else if (letter >= 'D')
                number = 3;
            else if (letter >= 'A')
                number = 2;
            System.out.println("The corresponding number is " + number);
        }else
            System.out.println(letter + " is an invalid input");
    }
}
