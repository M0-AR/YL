package Exercise_05._50;
/*
(Count uppercase letters) Write a program that prompts the user to enter a string
and displays the number of the uppercase letters in the string.
*/
import java.util.Scanner;
public class CountUpperCaseLetters {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();

        // Count how many letters with uppercase exist
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt( i ) >= 'A' && s.charAt( i ) <= 'Z') // or if (Character.isUpperCase(s.charAt(i)))
                count++;

        // Display result
        System.out.println("The number of uppercase letters is " +
                count);
    }
}
