package Exercise_03._34;

import java.util.Scanner;

public class PointOnLineSegment {//TODO I don't understand it
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter the three points for p0, p1 and p2.
        System.out.println("Enter three points for p0, p1 and p2: ");
        double x0 = scan.nextDouble(); double y0 = scan.nextDouble();
        double x1 = scan.nextDouble(); double y1 = scan.nextDouble();
        double x2 = scan.nextDouble(); double y2 = scan.nextDouble();

        // Calculate point in on line segment
        double position = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        System.out.println(position);

//        System.out.println((position <= ));


    }
}
