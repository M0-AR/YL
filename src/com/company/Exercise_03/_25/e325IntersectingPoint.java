package com.company.Exercise_03._25;

import java.util.Scanner;

public class e325IntersectingPoint {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        double x1,y1,x2,y2,x3,y3,x4,y4, a, b, c, d, e, f, intersectingX, intersectingY;

        //2
        System.out.println("Enter x1, y1, x2, y2, x3, y3, x4, y4");
        x1 =2;//= input.nextDouble();
        y1 =2;//= input.nextDouble();
        x2 =5;//= input.nextDouble();
        y2 =-1.0;// input.nextDouble();
        x3 =4.0;// input.nextDouble();
        y3 =2.0;// input.nextDouble();
        x4 =-1.0;// input.nextDouble();
        y4 =-2.0;// input.nextDouble();

        //3 Calculate the intersecting point
        a = y1 - y2;
        b = -1 * (x1 - x2);
        c = y3 - y4;
        d = -1 * (x3 - x4);
        e = (y1 - y2) * x1 - (x1 - x2) * y1;
        f = (y3 - y4) * x3 - (x3 - x4) * y3;

        //4 Display results
        if(a * d - b * c == 0)
        {
            System.out.println("The two lines are parallel");
        }
        else
        {
                intersectingX = (e * d - b * f) / (a * d - b * c);
                intersectingY = (a * f - e * c) / (a * d - b * c);
            System.out.println("THe intersecting point is at (" + String.format("%.5f",intersectingX) + ", " + String.format("%.5f",intersectingY) + ")");

        }
    }
}
