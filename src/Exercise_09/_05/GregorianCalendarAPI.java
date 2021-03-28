package Exercise_09._05;
/*********************************************************************************
 * (Use the GregorianCalendar class) Java API has the GregorianCalendar class in  *
 * the java.util package, which you can use to obtain the year, month, and day of *
 * a date. The no-arg constructor constructs an instance for the current date,    *
 * and the methods get(GregorianCalendar.YEAR), get(GregorianCalendar.MONTH), and *
 * get(GregorianCalendar.DAY_OF_MONTH) return the year, month, and day.           *
 * Write a program to perform two tasks:                                          *
 *                                                                                *
 * ■ Display the current year, month, and day.                                    *
 * ■ The GregorianCalendar class has the setTimeInMillis(long), which can be used *
 * to set a specified elapsed time since January 1, 1970. Set the value to        *
 * 1234567898765L and display the year, month, and day.                           *
 *********************************************************************************/
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
