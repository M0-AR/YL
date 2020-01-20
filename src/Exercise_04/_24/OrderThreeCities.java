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

    }
}
