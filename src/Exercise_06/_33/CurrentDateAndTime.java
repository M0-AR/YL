package Exercise_06._33;

public class CurrentDateAndTime {
    public static void main(String[] args) { // Fix the problem
        // Obtain the current time in millis seconds
        long currentMillisSeconds = System.currentTimeMillis();

        // Obtain the total time in seconds
        long seconds = (int) (currentMillisSeconds / 1000);

        // Obtain the current time in seconds
        int currentSeconds = (int) (seconds % 60);

        // Obtain the total time in minutes
        long minutes = seconds / 60;

        // Obtain the current time in minutes
        int currentMinutes = (int) (seconds % 60);

        // Obtain the total time in hours
        long hours = minutes / 60;

        // Obtain the current time in hours
        int currentHours = (int) (minutes % 24);

        // Obtain the total days
        int totalDays = (int) (hours / 24);
        if (currentHours > 0) totalDays++; //todo

        int currentYear = 2000;
        do {
            currentYear++;
        } while (getTotalDaysInYears( currentYear ) < totalDays);


        // Obtain month
        int totalNumbOfDaysInTheYear = totalDays -
                getTotalDaysInYears( currentYear - 1 );

        int currentMonth = 0;
        do {
            currentMonth++;
        } while (getTotalDaysInMonth( currentYear, currentMonth )
                < totalNumbOfDaysInTheYear);


        int currentDays = totalNumbOfDaysInTheYear -
                getTotalDaysInMonth( currentYear, currentMonth - 1 );

        // Display results
        String output = "Current date and time is " +
                currentMonth + "/" + currentDays + "/" + currentYear + " " +
                currentHours + ":" + currentMinutes + ":" + currentSeconds + " GMT";

        System.out.println(output);
    }

    /**
     * Get the total number of days from Jan 1 to the month in the year
     */
    public static int getTotalDaysInMonth(int year, int month) {
        int total = 0;

        // Add days for Jan to current month
        for (int i = 1; i <= month; i++)
            total += getNumOfDaysInMonth( year, i );

        return total;
    }

    /**
     * Get the number of days in a month
     */
    public static int getNumOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2)
            if (isLeapYear( year ))
                return 29;
            else
                return 28;

        return 0; // If month is incorrect.
    }


    /**
     * Get the total number of days from Jan 1, 1970 to the specified year
     */
    public static int getTotalDaysInYears(int year) {
        int total = 0;

        // Get the total days from 1970 to the current year
        for (int i = 1970; i <= year; i++)
            if (isLeapYear( i ))
                total += 366;
            else
                total += 355;

        return total;
    }


    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

}
