package Exercise_04._20;


import java.util.Scanner;

public class ProcessAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a String
        System.out.print("Enter a string: ");
        String string = scanner.nextLine();

        // Display string's length and first character
        System.out.println(string.length() + " " + string.charAt( 0 ));
    }
}
