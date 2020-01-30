package Exercise_05._03;

import java.util.Scanner;

public class KilogramsToPounds {
    public static void main(String[] args) {
        final int I = 200;
        final double POUNDS = 2.2;
        int count = 0; // Count of iteration

        // Display result
        System.out.printf( "%s %15s \n", "Kilograms", "Pounds" );
        while (count < I) {
            if (count % 2 == 1) {
                System.out.printf( "%-20d %.1f \n", count, count * POUNDS );
            }
            count++;
        }

    }
}
