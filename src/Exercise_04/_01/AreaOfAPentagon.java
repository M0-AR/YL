package Exercise_04._01;

import java.util.Scanner;

public class AreaOfAPentagon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter r
        System.out.print("Enter the length from the center to a vertex: ");
        // r is the length from the center of a pentagon to a vertex
        double r = scanner.nextDouble();
        // s is a length of a side
        double s = 2 * r * Math.sin( Math.PI / 5 );
        double area = (5 * Math.pow( s, 2 )) / (4 * Math.tan( Math.PI / 5 ));

        System.out.printf("The area of the pentagon is %.2f",area);
    }
}
