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



    }

}
