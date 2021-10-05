package Exercise_11._01;

import java.util.Scanner;

public class TestTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter three sides of the triangle
        System.out.println("Enter three sides of the triangle: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        // Prompt the user to enter a color
        System.out.println("Enter a color: ");
        String color = input.next();

        // Prompt the user to enter whether the triangle is filled
        System.out.println("Is the triangle filled (true / false)? ");
        boolean filled = input.nextBoolean();

        // Create triangle object with user input
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);

        // Display result
        System.out.println(triangle.toString());
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Color: " + triangle.getColor());
        System.out.println("Triangle is" + (triangle.isFilled() ? "" : " not ") + "filled");
    }
}
