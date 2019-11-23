package com.company.Exercise_03;
/*
(Geometry: point in a circle?) Write a program that prompts the user to enter a
point (x, y) and checks whether the point is within the circle centered at (0, 0)
with radius 10. For example, (4, 5) is inside the circle and (9, 9) is outside the
circle, as shown in Figure 3.7a.
*/
import java.util.Scanner;

public class e322pointInACircle {// done
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
//        First method
        //1
        double x, y, radius;

        //2
//        Prompt the user to enter a point
        System.out.print("Enter a point with two coordinates: ");
        x = input.nextDouble();
        y = input.nextDouble();

        //3
//        Compute the distance
        radius = Math.pow(x * x + y * y, 0.5);

        //4

        if (radius < 10){
            System.out.println("Point ("+x+", "+y+") is in the circle");
        }else {
            System.out.println("Point ("+x+", "+y+") is not in the circle");

        }

//        Second method
        //1
        double x1, y1;
        boolean inOrOutCircle;

        //2
//        Prompt the user to enter a point
        System.out.print("Enter a point with two coordinates: ");
        x1 = input.nextDouble();
        y1 = input.nextDouble();

        //3
//        Check the point is in or out of the circle
        inOrOutCircle = (Math.pow(x1 * x1 + y1 * y1, 0.5) <= 10);

        //4
//        Display results
        System.out.println("Point ("+x1+", "+y1+") is " + ((inOrOutCircle) ? "in ": "not in ") + "the circle");

    }
}
