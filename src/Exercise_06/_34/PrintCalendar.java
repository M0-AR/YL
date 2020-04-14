package Exercise_06._34;

import java.util.Scanner;

public class PrintCalendar {
    public static void main(String[] args) { // todo find bug
        // Prompt the user to enter year
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter year
        System.out.print( "Enter full year (e.g., 2001): " );
        int year = input.nextInt();

        // Prompt the user to enter month
        System.out.print( "Enter month in number between 1 and 12: " );
        int month = input.nextInt();

        // Print calendar for the month of the year
        printMonth( year, month );
    }

    /**
     * Print the calendar for a month in a year
     */
    static void printMonth(int year, int month) {
        // Print the headings of the calendar
        printMonthTitle( year, month );

        // Print the body of the calendar
        printMonthBody( year, month );
    }

    /**
     * Print the month title, e.g., May, 1999
     */
    static void printMonthTitle(int year, int month) {
        System.out.println( "         " + getMonthName( month )
                + " " + year );
        System.out.println( "-----------------------------" );
        System.out.println( " Sun Mon Tue Wed Thu Fri Sat" );
    }

    /**
     * Get the English name for the month
     */
    static String getMonthName(int month) {
        String monthName = null;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
        }
        return monthName;
    }

    /**
     * Print month body
     */
    static void printMonthBody(int year, int month) {
        // Get start day of the week for the first date in the month
        int startDay = getStartDay( year, month );

        // Get number of days in month
        int numberOfDaysInMonth = 2;// getNumberOfDaysInMOnth(year, month);todo

        // Pad space before the first day of the month
        int i = 0;
        for (int j = 0; j < startDay; j++)
            System.out.print( "    " );

        for (int j = 0; j <= numberOfDaysInMonth; j++) {
            if (i < 10)
                System.out.print( "   " + i);
            else
                System.out.print( "  " + i);

            if ((i + startDay) % 7 == 0)
                System.out.println();
        }

        System.out.println();
    }


    /**
     * Get the start day of month/1/year
     */
    static int getStartDay(int year, int month) {
        return getDayOfWeek( year, month, 1 );
    }

    public static int getDayOfWeek(int year, int month, int dayOfMonth) {
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

        int dayOfWeek = ((dayOfMonth + (int) ((month + 1) * 26.0 / 10))
                + k + (int) (k / 4.0) + (int) (j / 4.0) + 5 * j) % 7;

        return (dayOfWeek + 6 )% 7;
    }

    /** Get the number of days in a month */
    static int getNumberOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2) return isLeapYear(year) ? 29 : 28;

        return 0; // If month is incorrect
    }

    /** Determine if it is a leap year */
    static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}
