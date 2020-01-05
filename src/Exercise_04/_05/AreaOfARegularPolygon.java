package Exercise_04._05;

import java.util.Scanner;

public class AreaOfARegularPolygon {
    public static void main(String[] args) { // check g and Y
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter the number of sides and the length of side
        System.out.print("Enter the number of sides: ");
        int n = scanner.nextInt();
        System.out.print("Enter the side: ");
        double lengthOfSide = scanner.nextDouble();

        // Compute the area of hexagon
        double area = (n*Math.pow( lengthOfSide, 2 )) / (4*Math.tan(Math.PI/n));

        // Display result
        System.out.println("The area of the hexagon is " + area);
    }
}
