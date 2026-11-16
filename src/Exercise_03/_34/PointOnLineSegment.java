package Exercise_03._34;

import java.util.Scanner;

public class PointOnLineSegment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = input.nextDouble();
        double y0 = input.nextDouble();
        double x1 = input.nextDouble();
        double y1 = input.nextDouble();
        double x2 = input.nextDouble();
        double y2 = input.nextDouble();

        double crossProduct = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        boolean insideBounds = x2 >= Math.min(x0, x1)
                && x2 <= Math.max(x0, x1)
                && y2 >= Math.min(y0, y1)
                && y2 <= Math.max(y0, y1);

        if (Math.abs(crossProduct) < 1e-10 && insideBounds) {
            System.out.println("(" + x2 + ", " + y2 + ") is on the line segment");
        } else {
            System.out.println("(" + x2 + ", " + y2 + ") is not on the line segment");
        }

        input.close();
    }
}
