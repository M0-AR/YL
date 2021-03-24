package Exercise_09._05;

import java.util.GregorianCalendar;

public class GregorianCalendarAPI {
    public static void main(String[] args) {
        // Create a GregorianCalendar object
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // Display the current year, month, and day in format Mth/Day/Year
        System.out.println("\nCurrent year, month, and day in format Mth/Day/Year: ");
        System.out.println( gregorianCalendar.get(gregorianCalendar.MONTH) + "/" +
                            gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH )+ "/" +
                            gregorianCalendar.get(GregorianCalendar.YEAR));

        // Set elapsed time since January 1, 1970 to 12345678765L
        gregorianCalendar.setTimeInMillis( 12345678765L );

        // Display the year, month and day
        System.out.println("\nElapsed time since January 1, 1970 set to " +
                            "12345678765L in format Mth/Day/Year: ");
        System.out.println(gregorianCalendar.get(gregorianCalendar.MONTH) + "/" +
                            gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH) + "/" +
                            gregorianCalendar.get(gregorianCalendar.YEAR));

    }
}
