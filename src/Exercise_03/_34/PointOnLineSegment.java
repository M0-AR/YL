package Exercise_03._34;

import java.util.Scanner;

public class PointOnLineSegment {
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        System.out.println("Enter three points for p0, p1 and p2: ");
        double x0 = scan.nextDouble(), y0 = scan.nextDouble();
        double x1 = scan.nextDouble(), y1 = scan.nextDouble();
        double x2 = scan.nextDouble(), y2 = scan.nextDouble();

        double status = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        System.out.println(status);

//        System.out.println((status <= ));


    }
}
