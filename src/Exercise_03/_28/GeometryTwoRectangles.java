package Exercise_03._28;

import java.util.Scanner;

public class GeometryTwoRectangles { // Check Github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Rectangle number 1
        double width1 = 0, height1 = 0, centerX1 = 0, centerY1 = 0;

        // Rectangle number 2
        double width2 = 0, height2 = 0, centerX2 = 0, centerY2 = 0;

        // Prompt the user to enter two rectangles
        for (int i = 1; i <= 2 ; i++) {
            System.out.print("Enter r"+i+"'s center x-, y-coordinates, width, and height: ");
            if (i == 1){
                centerX1 = input.nextDouble();
                centerY1 = input.nextDouble();
                width1   = input.nextDouble();
                height1  = input.nextDouble();

            }else {
                centerX2 = input.nextDouble();
                centerY2 = input.nextDouble();
                width2   = input.nextDouble();
                height2  = input.nextDouble();
            }
        }// forLoop

        //
        double xDistance = centerX1 >= centerX2 ? centerX1 - centerX2: centerX2 - centerX1;
        double yDistance = centerY1 >= centerY2 ? centerY1 - centerY2: centerY2 - centerY1;


        //Display result
        if(xDistance <= (width1 - width2) / 2 && yDistance <= (height1 - height2) / 2)
            System.out.println("r2 is inside r1");
        else if (xDistance <= (width1 + width2) / 2 && yDistance <= (height1 + height2) / 2)
            System.out.println("r2 overlaps r1");
        else
            System.out.println("r2 does not overlap r1");
    }
}
