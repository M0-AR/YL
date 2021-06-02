package Exercise_09._10;

public class TestQuadraticEquation {
    public static void main(String[] args) {
        QuadraticEquation quadraticEquation = new QuadraticEquation( 1, 3, -10 );
        System.out.printf("Discriminant: %.2f\n", quadraticEquation.getDiscriminant());
        System.out.printf("Root1: %.2f  Root2: %.2f",
                quadraticEquation.getRoot1(), quadraticEquation.getRoot2());
    }
}
