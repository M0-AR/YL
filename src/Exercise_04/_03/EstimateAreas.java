package Exercise_04._03;

public class EstimateAreas {
    public static void main(String[] args) {
        double atlantaLat = 33.7489954;
        double atlantaLon = -84.3879824;
        double orlandoLat = 28.5383355;
        double orlandoLon = -81.3792365;
        double savannahLat = 32.0835407;
        double savannahLon = -81.0998342;
        double charlotteLat = 35.2270869;
        double charlotteLon = -80.8431267;

        double atlantaOrlando = distance(atlantaLat, atlantaLon, orlandoLat, orlandoLon);
        double orlandoSavannah = distance(orlandoLat, orlandoLon, savannahLat, savannahLon);
        double savannahAtlanta = distance(savannahLat, savannahLon, atlantaLat, atlantaLon);
        double savannahCharlotte = distance(savannahLat, savannahLon, charlotteLat, charlotteLon);
        double charlotteAtlanta = distance(charlotteLat, charlotteLon, atlantaLat, atlantaLon);

        double firstTriangle = triangleArea(atlantaOrlando, orlandoSavannah, savannahAtlanta);
        double secondTriangle = triangleArea(savannahAtlanta, savannahCharlotte, charlotteAtlanta);
        double area = firstTriangle + secondTriangle;

        System.out.printf("The estimated area enclosed by the four cities is %.2f square kilometers%n", area);
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double cosine = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(lon1 - lon2));
        cosine = Math.max(-1, Math.min(1, cosine));
        return 6371.01 * Math.acos(cosine);
    }

    public static double triangleArea(double side1, double side2, double side3) {
        double semiPerimeter = (side1 + side2 + side3) / 2;
        return Math.sqrt(semiPerimeter
                * (semiPerimeter - side1)
                * (semiPerimeter - side2)
                * (semiPerimeter - side3));
    }
}
