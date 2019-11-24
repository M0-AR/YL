package com.company.Exercise_03._27;

import java.util.Scanner;

public class GeometryPointsInTriangle { // Done
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //  Points of triangle
        int x1 = 0;   int y1 = 0;
        int x2 = 0;   int y2 = 100;
        int x3 = 200; int y3 = 0;

        // The point, which I will check it, if it is inside or outside of triangle
        // Prompt the user to enter a point
        System.out.print("Enter a point's x- and y-coordinates: ");
        double pX =  input.nextDouble();
        double pY =  input.nextDouble();

        // Determine if a point is inside or outside of a triangle
        // If all three equations are either positive or negative then
        // the point(pX, pY) is inside the triangle.
        double k1 = (pX - x1) * (y2 - y1) - (pY - y1) * (x2 - x1);
        double k2 = (pX - x2) * (y3 - y2) - (pY - y2) * (x3 - x2);
        double k3 = (pX - x3) * (y1 - y3) - (pY - y3) * (x1 - x3);


        // Display results
        System.out.println("The point " + (((k1>0&&k2>0&&k3>0) || k1<0&&k2<0&&k3<0)? "is ": "is not ") + "in the triangle");
    }

}
