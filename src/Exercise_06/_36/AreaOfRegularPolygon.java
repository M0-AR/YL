package Exercise_06._36;
/**
(Geometry: area of a regular polygon) A regular polygon is an n-sided polygon
in which all sides are of the same length and all angles have the same degree (i.e.,
the polygon is both equilateral and equiangular). The formula for computing the
area of a regular polygon is
				Area = (n * s^2) / (4 * tan(PI / n))
Write a method that returns the area of a regular polygon using the following header:
	public static double area(int n, double side)
Write a main method that prompts the user to enter the number of sides and the
side of a regular polygon and displays its area.
*/
import java.util.Scanner;

public class AreaOfRegularPolygon {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner( System.in );


        // Prompt the user to enter a number of size
        System.out.print( "Enter the number of sides: " );
        int n = input.nextInt();

        // Prompt the user to enter a side
        System.out.print( "Enter the side: " );
        double side = input.nextDouble();

        // Display result
        System.out.println("The area of the pentagon is "+area(n, side));
    }

    /** Compute the area of pentagon, where n is the number of side*/
    public static double area(int n,double side){
        return (n * Math.pow( side, 2 )) / (4 * Math.tan( Math.PI/ n ));
    }



}
