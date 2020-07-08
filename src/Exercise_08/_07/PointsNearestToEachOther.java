package Exercise_08._07;

public class PointsNearestToEachOther { // Todo github
    public static void main(String[] args) {
        double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
                {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1, 4, 2},
                {5.5, 4, -0.5}};

        // Invoke method
        computingDistance( points );
    }

    public static double[][] computingDistance(double[][] m) {
        double smallestDistance = Math.sqrt(Math.pow((m[1][0] -  m[0][0]), 2) +
                                            Math.pow((m[1][1] -  m[0][1]), 2) +
                                            Math.pow((m[1][2] -  m[0][2]), 2)); // To store a value of smallest distance
        // To store a point index that have smallest distance with another point
        int indexOfPoint1 = 0,
            indexOfPoint2 = 1;

        for (int i = 1; i < m.length; i++) {
            double distance = 0;
            for (int j = i + 1; j < m.length; j++) {
                for (int k = 0; k < m[i].length; k++) {
                     distance += Math.pow((m[j][k] -  m[i][k]), 2);
                }
                distance = Math.sqrt( distance );

                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    indexOfPoint1 = i;
                    indexOfPoint2 = j;
                }
            }
        }

        System.out.println(smallestDistance);
        System.out.println(indexOfPoint1);
        System.out.println(indexOfPoint2);
        return null;
    }
}
