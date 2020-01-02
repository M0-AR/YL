package Exercise_04._02;

import java.util.Scanner;

public class GreatCircleDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter the geographical latitude and
        // longitude of point
        System.out.print( "Enter point 1 (latitude and longitude) in degrees:" );
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        System.out.print( "Enter point 2 (latitude and longitude) in degrees:" );
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        // Compute the distance between the tow points
        double d = 6371.01 * Math.acos( Math.sin( Math.toRadians( x1 ) ) * Math.sin( Math.toRadians( x2 ) )
                + Math.cos( Math.toRadians( x1 ) ) * Math.cos( Math.toRadians( x2 ) ) *
                Math.cos( Math.toRadians( y1 - y2 ) ) );


        // Display result
        System.out.println("The distance between the two points is "+ d + " km");
    }
}
