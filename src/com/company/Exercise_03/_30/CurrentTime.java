package com.company.Exercise_03._30;
/*
    Display the hour using a 12-hour clock.
 */
import java.util.Scanner;

public class CurrentTime { // gitHUb
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        // Prompt the use to enter the time zone
        System.out.print("Enter the time zone offset to GMT: ");
        long timeZone = scan.nextLong();

        // Obtain the total seconds since midnight, Jan 1, 1970
        long seconds = System.currentTimeMillis() / 1000;
        long currentSeconds = seconds % 60;
//        System.out.println(currentSeconds);


        long minutes = seconds / 60;
        long currentMinutes = minutes % 60;
//        System.out.println(currentMinutes);

        long hours = minutes / 60;
        long currentHours = hours % 24;
        currentHours += timeZone;
//        System.out.println(currentHours);


//        Display results method 1.
        System.out.print("Current time is " + (currentHours % 12)  + ":" + currentMinutes + ":" + currentSeconds);
        if (currentHours < 12){
            System.out.println(" AM");
        }else {
            System.out.println(" PM");
        }
//        Display results method 1.
        System.out.println("Current time is " +((currentHours > 12) ? currentHours - 12 : currentHours) + ":" + currentMinutes + ":"
                + currentSeconds +((currentHours > 12) ? " PM": " AM")  ) ;
    }
}
