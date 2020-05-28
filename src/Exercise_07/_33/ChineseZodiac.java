package Exercise_07._33;

import java.util.Scanner;

public class ChineseZodiac {
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a year
        System.out.println( "Enter a year: " );
        int year = input.nextInt();

        String[] animalsName = {"monkey", "rooster", "dog",
                     "pig", "rat", "ox", "tiger", "rabbit",
                      "dragon", "snake", "horse", "sheep"};

        // Display result
        System.out.println(animalsName[year % 12]);
    }
}
