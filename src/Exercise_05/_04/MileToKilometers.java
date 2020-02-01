package Exercise_05._04;
/*
(Conversion from miles to kilometers) Write a program that displays the following
table (note that 1 mile is 1.609 kilometers):
Miles           Kilometers
1               1.609
2               3.218
...
9               14.481
10              16.090
*/
public class MileToKilometers {
    public static void main(String[] args) { // g and y
        final double MILE_TO_KILOMETERS = 1.609;

        // Display result
//        System.out.printf( "%s %15s\n","Miles", "Kilometers" ); or
        System.out.println("Miles\t\tKilometers");
        System.out.println("-------------------------------");
        // Display table
        for (int miles = 1; miles <= 10; miles++) {
//            System.out.printf( "%d %15.3f\n", i, (i*MILE_TO_KILOMETERS) ); or
            System.out.println(miles + "\t\t\t" + miles * MILE_TO_KILOMETERS);

        }
    }
}
