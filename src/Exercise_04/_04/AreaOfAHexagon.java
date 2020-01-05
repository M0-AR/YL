package Exercise_04._04;

import java.util.Scanner;

public class AreaOfAHexagon {
    public static void main(String[] args) { // check g and Y
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter the side
        System.out.print("Enter the side: ");
        double side = scanner.nextDouble();

        // Compute the area of hexagon
        double area = (6*Math.pow( side, 2 )) / (4*Math.tan(Math.PI/6));

        // Display result
        System.out.printf("The area of the hexagon is %.2f" , area);
    }
}
