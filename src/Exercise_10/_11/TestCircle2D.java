package Exercise_10._11;

public class TestCircle2D {
    public static void main(String[] args) {
        // Creates Circle2D object
        Circle2D c1 = new Circle2D(2, 2, 5.5);

        // Displays its area and perimeter
        System.out.println("Area: " + c1.getArea());
        System.out.println("Perimeter: " + c1.getPerimeter());

        // Display the result of c1.contains(3, 3),
        // c1.contains(new Circle2D(4, 5, 10.5)), and
        // c1.overlaps(new Circle2D(3, 5, 2.3)).
        System.out.println("Contains (3, 3): " + c1.contains(3, 3));
        System.out.println("Contains Circle2D(4, 5, 10.5): " +
                c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.println("Overlaps Circle2D(3, 5, 2.3): " +
                c1.overlaps(new Circle2D(3, 5, 2.5)));
    }
}
