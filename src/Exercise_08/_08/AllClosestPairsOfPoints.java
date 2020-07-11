package Exercise_08._08;

public class AllClosestPairsOfPoints { // Todo find bugs
    public static void main(String[] args) {
        double[][] points = {{0, 0}, {1, 1}, {-1, -1},
                            {2, 2}, {-2, -2}, {-3, -3},
                            {-4, -4}, {5,5}};


        findShortDistance( points );
    }


    /** Find shortest distance and display result */
    public static void findShortDistance(double[][] m) {

        double shortestDistance = // To store a value of smallest distance
                distance( m[0][0], m[0][1], m[1][0], m[1][1] ); // To compute distance

        // To store a point index that have smallest distance with another point
        int indexOfPoint1 = 0,
                indexOfPoint2 = 1;

        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                double distance = distance( m[i][0], m[i][1],
                        m[j][0], m[j][1] ); // Find distance

                if (distance < shortestDistance || distance == shortestDistance) {
                    shortestDistance = distance;
                    indexOfPoint1 = i;
                    indexOfPoint2 = j;

                    System.out.println("The closest two points are (" + m[i][0] + ", " + m[i][1] + ")" +
                            "(" + m[j][0] + ", " + m[j][1] + ")");
                }


            }
        }



    }

    /** Compute distance between two points */
    public static double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt( Math.pow( x2 - x1, 2 ) + Math.pow( y2 - y1, 2 ) );
    }
}
