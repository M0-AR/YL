package com.company.Exercise_02._07;

import java.util.Scanner;

public class FindTheNumberOfYears {
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );
        final int  MINUTESPERHOUR = 60;
        final int  HOURSPERDAY = 24;
        final int  DAYSPERYEAR = 365;

        // Prompts the user to enter the minutes (e.p., 1 billion)
        System.out.print("Enter the number of minutes: ");
        int minutes = scan.nextInt();

        // Cumpute the number of year and day
        int years = minutes / (MINUTESPERHOUR * HOURSPERDAY * DAYSPERYEAR);
        int reminderOfMinutes = minutes % (MINUTESPERHOUR * HOURSPERDAY * DAYSPERYEAR);
        int days  = reminderOfMinutes / (MINUTESPERHOUR * HOURSPERDAY);

        // Displays how many years and days in minutes
        System.out.println(minutes + " minutes is approximately " + years + " years and " + days + " days" );
    }




}
