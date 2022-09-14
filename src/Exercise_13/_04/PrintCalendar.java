package Exercise_13._04;

import java.util.HashMap;
import java.util.Map;

public class PrintCalendar {
    private Map<Integer, String> months;

    public PrintCalendar() {
        this.months = new HashMap<Integer, String>() {{
            put(1, "January");  put(2, "February");  put(3, "March");
            put(4, "April");    put(5, "May");       put(6, "June");
            put(7, "July");     put(8, "August");    put(9, "September");
            put(10, "October"); put(11, "November"); put(12, "December");
        }};

    }

    /**
     * Print the calendar for a month in a year
     */
    public void printCalendar(int month, int year) {
        // Print the headings of the calendar
        printMonthTitle(month, year);

        // Print the body of the calendar
        printMonthBody(month, year);
    }

    /**
     * Print the month title, e.g., May, 1999
     */
    public void printMonthTitle(int month, int year) {
        System.out.println( "         " + this.months.get(month)
                + " " + year );
        System.out.println( "-----------------------------" );
        System.out.println( " Sun Mon Tue Wed Thu Fri Sat" );
    }


    /**
     * Print month body
     */
    public void printMonthBody(int month, int year) {
        // Get start day of the week for the first date in the month
        int startDay = getStartDay(month, year);

        // Get number of days in month
        int numberOfDaysInMonth = getNumberOfDaysInMonth( year, month );

        // Pad space before the first day of the month
        int i = 0;
        for (i = 0; i < startDay; i++)
            System.out.print( "    " );

        for (i = 1; i <= numberOfDaysInMonth; i++) {
            if (i < 10)
                System.out.print( "   " + i );
            else
                System.out.print( "  " + i );

            if ((i + startDay) % 7 == 0)
                System.out.println();
        }

        System.out.println();
    }


    /**
     * Get the start day of month/1/year
     */
    public int getStartDay(int month, int year) {
        return getDayOfWeek(1, month, year);
    }

    public int getDayOfWeek(int day, int month, int year) {
        if (month == 1) {
            month = 13;
            year--;
        }

        if (month == 2) {
            month = 14;
            year--;
        }

        int k = year % 100;
        int j = year / 100;

        int dayOfWeek = ((day + (int) ((month + 1) * 26.0 / 10))
                + k + (int) (k / 4.0) + (int) (j / 4.0) + 5 * j) % 7;

        return (dayOfWeek + 6) % 7;
    }

    /**
     * Get the number of days in a month
     */
    public int getNumberOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2) return isLeapYear( year ) ? 29 : 28;

        return 0; // If month is incorrect
    }

    /**
     * Determine if it is a leap year
     */
    public boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}
