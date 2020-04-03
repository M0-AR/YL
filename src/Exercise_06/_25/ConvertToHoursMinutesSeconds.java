package Exercise_06._25;

import java.util.Scanner;

/*
(Convert milliseconds to hours, minutes, and seconds) Write a method that converts
milliseconds to hours, minutes, and seconds using the following header:
public static String convertMillis(long millis)
The method returns a string as hours:minutes:seconds. For example,
convertMillis(5500) returns a string 0:0:5, convertMillis(100000) returns
a string 0:1:40, and convertMillis(555550000) returns a string 154:19:10.
*/
public class ConvertToHoursMinutesSeconds {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter milliseconds
        System.out.print( "Enter milliseconds: " );
        long milliSeconds = input.nextLong();

        // Display result
        System.out.println( "hours:minutes:seconds: "+convertMillis( milliSeconds ) );
    }

    /**
     * Convert millis to hours, minuet and second
     */
    public static String convertMillis(long millis) {
        // Obtain total second
        millis /= 1000;

        // Obtain current minutes and seconds
        String currentMinuteAndSecond = "";
        for (int i = 0; i < 2; i++) {
            currentMinuteAndSecond = ":" + millis % 60 + currentMinuteAndSecond;
            millis /= 60;
        }
        return millis + currentMinuteAndSecond;
    }



}
