package Exercise_06._24;
/*
(Display current date and time) Listing 2.7, ShowCurrentTime.java, displays the
current time. Improve this example to display the current date and time. The calendar
example in Listing 6.12, PrintCalendar.java, should give you some ideas on
how to find the year, month, and day.
*/
// Current date and time is 4/3/2020 6:34:12 GMT
public class DisplayCurrentDateAndTime {
    public static void main(String[] args) {
        // Obtain the total milliseconds since the midnight, Jan 1, 1970
        long totalMilliSeconds = System.currentTimeMillis();

        // Obtain the total milliseconds since the midnight, Jan 1, 1970
        long totalSeconds = totalMilliSeconds / 1000;

        // Compute the current second in the minute in the hour
        int currentSecond = (int) (totalSeconds % 60);

        // Obtain the total minutes
        long totalMinutes = totalSeconds / 60;

        // Compute the current minute in the hour
        int currentMinute = (int) (totalMinutes % 60);

        // Obtain the total hours
        long totalHours = totalMinutes / 60;

        // Compute the current hour
        int currentHour = (int) (totalHours % 24);

        // Compute the total days
        int totalDays = (int) (totalHours / 24);
        if (currentHour > 0) totalDays++;

        // Obtain Year
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

        // Obtain Day
        int currentDay = totalNumbOfDaysInTheYear -
                getTotalDaysInMonth( currentYear, currentMonth - 1  );

        // Display results
        String output = "Current date and time is " +
                currentMonth + "/" + currentDay + "/" + currentYear + " " +
                currentHour + ":" + currentMinute + ":" + currentSecond + " GMT";

        System.out.println(output);

    }

    /** Get the total number of days from Jan 1, 1970 to the specified year*/
    public static int getTotalDaysInYears(int year) {
        int total = 0;

        // Get the total days from 1970 to the specified year
        for (int i = 1970; i <= year ; i++)
            if (isLeapYear(i))
                total = total + 366;
            else
                total = total + 365;

        return total;
    }

    /**
     * Get the total number of days from Jan 1 to the month in the year
     */
    public static int getTotalDaysInMonth(int year, int month) {
        int total = 0;

        // Add days from Jan to the month
        for (int i = 1; i <= month; i++)
            total = total +  getNumOfDaysInMonth(year, i);

        return total;
    }

    /** Get the number of days in a month */
    public static int getNumOfDaysInMonth(int year, int month){
        if (month == 1 || month==3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2)
            if (isLeapYear(year))
                return 29;
            else
                return 28;

        return 0; // If month is incorrect.
    }



    /** Determine if it is a leap year */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
