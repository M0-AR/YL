package Exercise_08._07;

/*********************************************************************************
 * (Points nearest to each other) Listing 8.3 gives a program that finds two      *
 * points in a two-dimensional space nearest to each other. Revise the program so *
 * that it finds two points in a three-dimensional space nearest to each other.   *
 * Use a two-dimensional array to represent the points. Test the program using    *
 * the following points:                                                          *
 * double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},                      *
 * {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2},                          *
 * {5.5, 4, -0.5}};                                                               *
 * The formula for computing the distance between two points (x1, y1, z1) and     *
 * (x2, y2, z2) is √(x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2.                      *
 *********************************************************************************/

public class PointsNearestToEachOther {
    public static void main(String[] args) {
        double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
                {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2},
                {5.5, 4, -0.5}};

        // Invoke method
        findShortDistance( points );
    }

    /** Find shortest distance between two points and display result */
    public static void findShortDistance(double[][] m) {

        double shortestDistance = // To store a value of smallest distance
                distance( m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2]); // To compute distance

        // To store a point index that have smallest distance with another point
        int indexOfPoint1 = 0,
            indexOfPoint2 = 1;

        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                double distance = distance(m[i][0], m[i][1], m[i][2],
                        m[j][0], m[j][1], m[j][2]); // Find distance

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    indexOfPoint1 = i;
                    indexOfPoint2 = j;
                }
            }
        }


        // Display result
        System.out.println("The shortest distance is " + shortestDistance);

        System.out.println("First point" + " " + m[indexOfPoint1][0]
                                         + " " + m[indexOfPoint1][1]
                                         + " " + m[indexOfPoint1][2]);

        System.out.println("Second point" + " " + m[indexOfPoint2][0]
                                         + " " + m[indexOfPoint2][1]
                                         + " " + m[indexOfPoint2][2]);

    }

    /** Compute distance between two points */
    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt( Math.pow( x2 - x1, 2 ) + Math.pow( y2 - y1, 2 ) + Math.pow( z2 - z1, 2 ) );
    }
}
