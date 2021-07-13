package Exercise_10._12;

public class TestTriangle2D {
    public static void main(String[] args) {
        Triangle2D r1 = new Triangle2D(2, 2, 5.5, 4.9, 9.0, 10.3);
        System.out.println("Area is " + r1.getArea());
        System.out.println("Perimeter is " + r1.getPerimeter());
        System.out.println(r1.contains(3, 3));
        System.out.println(r1.contains(new Triangle2D(4, 5, 10.5, 3.2, -0.5, -10.5)));
        System.out.println(r1.overlaps(new Triangle2D(3, 5, 2.3, 6.7, 5.5, 8.8)));
    }
}
