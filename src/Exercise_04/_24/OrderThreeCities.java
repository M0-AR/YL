package Exercise_04._24;

import java.util.Scanner;

public class OrderThreeCities { // g and y
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        String[] cities = new String[3];

        // Prompt the user to enter three cities
        System.out.print( "Enter the first city: " );
        cities[0] = scanner.nextLine();
        System.out.print( "Enter the second city: " );
        cities[1] = scanner.nextLine();
        System.out.print( "Enter the third city: " );
        cities[2] = scanner.nextLine();

        // Sorting cities string array
        for (int i = 0; i < cities.length ; i++) {
            for (int j = i + 1; j <cities.length ; j++) {
                if (cities[i].compareToIgnoreCase( cities[j] ) > -1){
                    String temp = cities[i];
                    cities[i] = cities[j];
                    cities[j] = temp;
                }
            }
        }

        // Display the result ascending order
        System.out.print( "The three cities in alphabetical order are " );
        for (String city : cities) {
            System.out.print(city + " ");
        }

        // Second Solution
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first city: ");
        String s1 = input.nextLine();
        System.out.print("Enter the second city: ");
        String s2 = input.nextLine();
        System.out.print("Enter the third city: ");
        String s3 = input.nextLine();

        // or String temp out if
        if (s1.compareTo(s2) > 0) {
            // Swap s1 with s2
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        if (s2.compareTo(s3) > 0) {
            // Swap s2 with s3
            String temp = s2;
            s2 = s3;
            s3 = temp;
        }

        if (s1.compareTo(s2) > 0) {
            // Swap s1 with s2
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        System.out.print("The three cities in alphabetical order are "
                + s1 + " " + s2 + " " + s3);

    }
}
