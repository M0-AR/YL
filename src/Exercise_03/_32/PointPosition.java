package Exercise_03._32;
/*
(Geometry: point position) Given a directed line from point p0(x0, y0) to p1(x1,
y1), you can use the following condition to decide whether a point p2(x2, y2) is
on the left of the line, on the right, or on the same line
*/
import java.util.Scanner;

public class PointPosition {
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter three points
        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = scan.nextDouble(), y0 = scan.nextDouble(),
               x1 = scan.nextDouble(), y1 = scan.nextDouble(),
               x2 = scan.nextDouble(), y2 = scan.nextDouble();

        // Calculate p2 if to left, on or right
        double p2 = (x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0);

        // Display result
        System.out.println("p2 is on the " + ((p2 > 0) ? "left side of the":
                        (p2 == 0) ? "same": "right side of the") + " line" );

    }
}
