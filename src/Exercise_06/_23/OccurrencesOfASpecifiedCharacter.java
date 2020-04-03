package Exercise_06._23;
/*
(Occurrences of a specified character) Write a method that finds the number of
occurrences of a specified character in a string using the following header:
public static int count(String str, char a)
For example, count("Welcome", 'e') returns 2. Write a test program that
prompts the user to enter a string followed by a character and displays the number
of occurrences of the character in the string.
*/
import java.util.Scanner;

public class OccurrencesOfASpecifiedCharacter {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a word and a letter
        System.out.print("Enter a word and letter: ");
        String s = input.next();
        char ch = input.next().charAt( 0 );

        // Display output
        System.out.println("The number of occurrences of the character in the string is " +
                count( s, ch ));
    }

    public static int count(String str, char a){
        int count = 0;
        for (int i = 0; i < str.length() ; i++)
            if (str.charAt( i ) == a)
                count++;
        return count;
    }
}
