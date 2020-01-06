package Exercise_04._05;
/*
(Geometry: area of a regular polygon) A regular polygon is an n-sided polygon in
which all sides are of the same length and all angles have the same degree (i.e., the
polygon is both equilateral and equiangular). The formula for computing the area
of a regular polygon is
Here, s is the length of a side. Write a program that prompts the user to enter the
number of sides and their length of a regular polygon and displays its area.
*/

import java.util.Scanner;

public class AreaOfARegularPolygon {
    public static void main(String[] args) { // check g and Y
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter the number of sides and the length of side
        System.out.print("Enter the number of sides: ");
        int numberOfSide = scanner.nextInt();
        System.out.print("Enter the side: ");
        double lengthOfSide = scanner.nextDouble();

        // Compute the area of hexagon
        double area = (numberOfSide*Math.pow( lengthOfSide, 2 )) /
                                    (4*Math.tan(Math.PI/numberOfSide));

        // Display result
        System.out.println("The area of the hexagon is " + area);
    }
}
