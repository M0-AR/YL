package Exercise_05._05;
/*
(Conversion from kilograms to pounds and pounds to kilograms) Write a program
that displays the following two tables side by side:
Kilograms Pounds    |    Pounds Kilograms
1            2.2    |    20          9.09
3            6.6    |    25         11.36
...
197        433.4    |    510       231.82
199        437.8    |    515       234.09
*/
public class KilogramsToPoundsAndPoundsToKilograms {
    public static void main(String[] args) { // g
        final double POUNDS_TO_KILOGRAMS = 2.2;

        // Display result
        System.out.printf( "%s %15s %4s %9s %18s\n", "Kilograms", "Pounds", "|", "Pounds", "Kilograms" );

        // Display table

        for (int i = 1, amountOfPound = 20; i < 200; amountOfPound += 5, i += 2) {
            System.out.printf( "%-7d %15.1f %6s %7d %15.2f\n", i, (i * POUNDS_TO_KILOGRAMS)
                    , "|", amountOfPound, (amountOfPound / POUNDS_TO_KILOGRAMS) );


        }

    }
}
