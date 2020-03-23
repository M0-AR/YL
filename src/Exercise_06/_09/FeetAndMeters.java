package Exercise_06._09;
// (Conversions between feet and meters) Write a class that contains the following
// two methods:

// /** Convert from feet to meters */
// public static double footToMeter(double foot)

// /** Convert from meters to feet */
// public static double meterToFoot(double meter)

// The formula for the conversion is:
// meter = 0.305 * foot
// foot = 3.279 * meter

// Write a test program that invokes these methods to display the following tables://
//     Feet     Meters     |    Meters    Feet
//     -------------------------------------------
//     1.0      0.305      |    20.0      65.574
//     2.0      0.610      |    25.0      81.967
//     ...
//     9.0      2.745      |    60.0      196.721
//     10.0     3.050      |    65.0      213.115

public class FeetAndMeters {
    public static void main(String[] args) { // Github and y and see how to print last line by a smart way
        printCelsiusAndFahrenheit();
    }

    /** Convert from feet to meters */
    public static double footToMeter(double foot){
        return 0.305 * foot;
    }

    /** Convert from  meters to feet  */
    public static double meterToFoot(double meter){
        return 3.279 * meter;
    }

    public static void printCelsiusAndFahrenheit(){
        System.out.printf( "%-15s%-15s%-5s%-15s%-15s\n", "Feet", "Meters","|     ", "Meters", "Feet" );
        System.out.println("----------------------------------------------------------");

        for (double feet = 1.0, meters = 20.2; feet <= 10.0;feet++, meters+=5)
            System.out.printf( "%-15.1f%-15.3f%-5s%-15.1f%-15.3f\n", feet, footToMeter( feet ), "|     ", meters,meterToFoot( meters ) );
    }
}
