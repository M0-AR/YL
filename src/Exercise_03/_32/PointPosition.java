package Exercise_03._32;

import java.util.Scanner;

public class PointPosition {// Check solution and github
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter three points
        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = scan.nextDouble(), y0 = scan.nextDouble(),
               x1 = scan.nextDouble(), y1 = scan.nextDouble(),
               x2 = scan.nextDouble(), y2 = scan.nextDouble();

        // Calculate p2 if to left, on or right
        double p2 = (x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0);

        //
        System.out.println( ((p2 > 0) ? "p2 is on the left side of the line": (p2 == 0) ? "p2 is on the same line": "p2 is on the right side of the line") );

    }
}
