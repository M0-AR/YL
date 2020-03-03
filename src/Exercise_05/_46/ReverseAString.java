package Exercise_05._46;

import java.util.Scanner;

public class ReverseAString {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a String value
        System.out.println("Enter a ");
        String s = input.nextLine();

        // Reverse the string and display string
        for (int i = s.length()-1; i >=0 ; i--) {
            System.out.print( s.charAt( i ) );
        }
    }
}
