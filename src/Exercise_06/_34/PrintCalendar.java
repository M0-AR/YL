package Exercise_06._34;
/**
Adapted by Sleekpanther from SquirrelCoder's initial idea from 29-Jan-17.
Uses arrays, even though they technically aren't introduced until chapter 7. But it simplifies this a lot
6.34 (Print calendar) Programming Exercise 3.21 uses Zeller's congruence to calculate
the day of the week. Simplify Listing 6.12, PrintCalendar.java, using Zeller's
algorithm to get the start day of the month.
Exercise 3.21 details
(Science: day of the week) Zeller's congruence is an algorithm developed by
Christian Zeller to calculate the day of the week. The formula is:
h=(q + (26(m+1))/10 + k + k/4 + j/4 +5*j)%7
h is the day of the week (0: Saturday, 1: Sunday, 2: Monday, 3: Tuesday, 4: Wednesday, 5: Thursday, 6: Friday).
q is the day of the month.
m is the month (3: March, 4: April, ..., 12: December). January and February are counted as months 13 and 14 of the previous year.
j is the century (i.e. year/100)
k is the year of the century (i.e., year % 100).
Note that the division in the formula performs an integer division. Write a program
that prompts the user to enter a year, month, and day of the month, and
displays the name of the day of the week.
(Hint: January and February are counted as 13 and 14 in the formula, so you need
to convert the user input 1 to 13 and 2 to 14 for the month and change the year to the previous year.)
 */

import java.util.Scanner;

public class PrintCalendar {
    public static void main(String[] args) {
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

        return (dayOfWeek + 6) % 7;
    }

    /**
     * Get the number of days in a month
     */
    static int getNumberOfDaysInMonth(int year, int month) {
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
    static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}
