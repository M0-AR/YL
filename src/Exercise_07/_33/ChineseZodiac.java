package Exercise_07._33;
/*********************************************************************************
 * (Culture: Chinese Zodiac) Simplify Listing 3.9 using an array of strings to    *
 * store the animal names.                                                        *
 *********************************************************************************/
import java.util.Scanner;

public class ChineseZodiac {
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create array of Chinese Zodiac animal names
        String[] animalsName = {"monkey", "rooster", "dog",
                "pig", "rat", "ox", "tiger", "rabbit",
                "dragon", "snake", "horse", "sheep"};

        // Prompt the user to enter a year
        System.out.println( "Enter a year: " );
        int year = input.nextInt();

        // Display Chinese Zodiac sign for the given year
        System.out.println(animalsName[year % 12]);
    }
}
