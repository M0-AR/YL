package Exercise_05._49;

import java.util.Scanner;

public class CountVowelsAndConsonants {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a string
        System.out.println( "Enter a string: " );
        String s = input.nextLine();
        s = s.replaceAll( " ", ""); // To not count spaces

        // Count how many vowels letter exist in s
        int vowelsCounter = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.toUpperCase().charAt( i ) == 'A' || s.toUpperCase().charAt( i ) == 'E' ||s.toUpperCase().charAt( i ) == 'I' ||s.toUpperCase().charAt( i ) == 'O' ||s.toUpperCase().charAt( i ) == 'U')
                vowelsCounter++;

        // Display result
        System.out.println("The number of vowels is " + vowelsCounter);
        System.out.println("The number of consonants is " + (s.length() - vowelsCounter));
    }
}
