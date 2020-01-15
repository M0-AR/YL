package Exercise_04._15;
/*
(Phone key pads) The international standard letter/number mapping found on the
telephone is shown below:
Write a program that prompts the user to enter a letter and displays its corresponding
number.
*/
import java.util.Scanner;

public class PhoneKeyPads {
    public static void main(String[] args) { // con g
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a letter
        System.out.print("Enter a letter: ");
        char letter = scanner.next().charAt( 0 );

        int number = 0;
        switch (Character.toLowerCase( letter )){
            case 'a': number = 1;
            case 'b': number = 1;
            case 'c': number = 1;//.....
        }


        // Display result
        System.out.println("The Corresponding number is " + number);

    }
}
