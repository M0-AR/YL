package Exercise_03._29;

import java.util.Scanner;

public class TwoCircles {
    public static void main(String[] args) { // Check github and write the text
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter circle1's center x-, y-coordinates, and radius:
        System.out.print("Enter circle1's center x-, y-coordinates, and radius: ");
        double x1 = scan.nextDouble();
        double y1 = scan.nextDouble();
        double r1 = scan.nextDouble();

        // Prompt the user to enter circle2's center x-, y-coordinates, and radius:
        System.out.print("Enter circle1's center x-, y-coordinates, and radius: ");
        double x2 = scan.nextDouble();
        double y2 = scan.nextDouble();
        double r2 = scan.nextDouble();

        // Calculate the distance between to circles
        double distance = Math.sqrt( Math.pow(x2 - x1, 2) +  Math.pow(y2 - y1, 2) );


        // If the distance between the two centers smaller or equal (r1 - r2) so circle2 is inside circle1
        if(distance <= (r1 - r2))
        {
            System.out.println("Circle2 is inside circle1");
        }
        // If the distance between the two centers smaller or equal (r1 + r2) so circle2 is overlaps circle1
        else if(distance <= (r1 + r2))
        {
            System.out.println("Circle2 overlaps circle1 circle1");
        }

        else
            {
                System.out.println("Circle2 does not overlap circle1");
            }

    }
}
