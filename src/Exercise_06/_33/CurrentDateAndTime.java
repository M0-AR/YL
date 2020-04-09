package Exercise_06._33;

public class CurrentDateAndTime { // 24
    public static void main(String[] args) {
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
        //if (currentHours > 0) totalDays++; //todo

        int currentYear = 2000;
        do {
            currentYear++;
        } while (getTotalDaysInYears( currentYear ) < totalDays);


        // Obtain month
        int totalNumbOfDaysInTheYear = totalDays -
                getTotalDaysInYears( currentYear - 1 ); // todo why -1

        // con

    }


    /** Get the total number of days from Jan 1, 1970 to the specified year*/
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
