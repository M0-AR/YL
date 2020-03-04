package Exercise_05._48;
/*
(Process string) Write a program that prompts the user to enter a string and displays
the characters at odd positions.
*/
import java.util.Scanner;

public class ProcessString {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();

        // Display result
        for (int i = 0; i < s.length(); i+=2)
            System.out.print(s.charAt( i ));

        System.out.println();

    }
}
