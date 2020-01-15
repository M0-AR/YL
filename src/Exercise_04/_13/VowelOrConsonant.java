package Exercise_04._13;
/*
(Vowel or consonant?) Write a program that prompts the user to enter a letter and
check whether the letter is a vowel or consonant.
*/
import java.util.Scanner;

public class VowelOrConsonant {
    public static void main(String[] args) { // g
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a letter
        System.out.print("Enter a letter: ");
        char letter = scanner.next().charAt( 0 );

        // or if (Character.isLetter(letter))
        if (Character.toLowerCase( letter ) >='a'&& Character.toLowerCase( letter )<='z' ){
            switch (Character.toLowerCase( letter )){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    System.out.println(letter + " is a vowel"); break;
                default:
                    System.out.println(letter + " is a consonant");
                    break;
            }
        }else {
            System.out.println(letter + " is an invalid input");
        }
    }
}
