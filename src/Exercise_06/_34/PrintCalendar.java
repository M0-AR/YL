package Exercise_06._34;

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
        printMonth(year, month);
    }

    /** Print the calendar for a month in a year */
    static void printMonth(int year, int month){
        // Print the headings of the calendar


        // Print the body of the calendar

    }
}
