package Exercise_04._03;
/*
(Geography: estimate areas) Find the GPS locations for Atlanta, Georgia;
Orlando, Florida; Savannah, Georgia; and Charlotte, North Carolina from
www.gps-data-team.com/map/ and compute the estimated area enclosed by these
four cities. (Hint: Use the formula in Programming Exercise 4.2 to compute the
distance between two cities. Divide the polygon into two triangles and use the
formula in Programming Exercise 2.19 to compute the area of a triangle.)
*/

public class EstimateAreas {
    public static void main(String[] args) {

        final double atlantaX = 33.7489954;
        double atlantaY = -84.3879824;
        double charlotteX = 35.2270869;
        double charlotteY = -80.8431267;
        double savannahX = 32.0835407;
        double savannahY = -81.0998342;
        double orlandoX = 28.5383355;
        double orlandoY = -81.3792365;

        // Compute the distance between the points
        double d = 0;
        for (int i = 0; i < 4 ; i++) {
        }
        double dAC = 6371.01 * Math.acos( Math.sin( Math.toRadians( atlantaX ) ) * Math.sin( Math.toRadians( charlotteX ) )
                + Math.cos( Math.toRadians( atlantaX) ) * Math.cos( Math.toRadians( charlotteX ) ) *
                Math.cos( Math.toRadians( atlantaY - charlotteY ) ) );




    }
}
