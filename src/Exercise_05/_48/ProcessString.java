package Exercise_05._48;

import java.util.Scanner;

public class ProcessString {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();

        // Display result
        for (int i = 0; i < s.length(); i+=2) {
            System.out.print(s.charAt( i ));
        }
    }
}
