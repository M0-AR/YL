package Exercise_05._03;
/*
(Conversion from kilograms to pounds) Write a program that displays the following
table (note that 1 kilogram is 2.2 pounds):
Kilograms     Pounds
1                2.2
3                6.6
...
197            433.4
199            437.8
*/
public class KilogramsToPounds {
    public static void main(String[] args) {
        final double POUNDS_PER_KILOGRAM = 2.2;
        int count = 0; // Count of iteration

        // Display result
        System.out.printf( "%s %15s \n", "Kilograms", "Pounds" );

        // 1. while method: Display table
        while (count < 200) {
            if (count % 2 == 1) {
                System.out.printf( "%-20d %.1f \n", count, count * POUNDS_PER_KILOGRAM );
            }
            count++;
        }


        // 2. for method: Display table
        for (int i = 1; i < 200; i+= 2) {
            System.out.printf( "%-20d %.1f \n", count, count * POUNDS_PER_KILOGRAM );
        }


    }
}
