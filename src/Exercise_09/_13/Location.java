package Exercise_09._13;
            // UML diagram
/*******************************************
 *                Location                 *
 *-----------------------------------------*
 * + locations: int[][]                    *
 * + maxValue: double                      *
 * ----------------------------------------*
 * + locateLarges(a: double[][]): Location *
 *******************************************/
public class Location {
    public int[][] locations;
    public double maxValue;

    private Location(int[][] locations, double maxValue) {
        this.locations = locations;
        this.maxValue = maxValue;
    }

    public static Location locateLargest(double[][] a) {
       int[][] maxLocation = new int[1][2];
       double maxValue = Double.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > maxValue) {
                    maxValue = a[i][j];
                    maxLocation[0][0] = i;
                    maxLocation[0][1] = j;
                }
            }
        }

        return new Location(maxLocation, maxValue);
    }
}
