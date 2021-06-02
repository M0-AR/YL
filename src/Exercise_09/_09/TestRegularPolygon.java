package Exercise_09._09;

public class TestRegularPolygon {
    public static void main(String[] args) {
        RegularPolygon regularPolygon1 = new RegularPolygon( 6, 4 );
        System.out.println("Display Perimeter and Area of first Regular Polygon:");
        System.out.printf("Perimeter: %.2f\n", regularPolygon1.getPerimeter());
        System.out.printf("Area: %.2f", regularPolygon1.getArea());

        System.out.println();

        RegularPolygon regularPolygon2 = new RegularPolygon(10, 4, 5.8, 7.8);
        System.out.println("Display Perimeter and Area of second Regular Polygon:");
        System.out.printf("Perimeter: %.2f\n", regularPolygon2.getPerimeter());
        System.out.printf("Area: %.2f", regularPolygon2.getArea());
    }
}
