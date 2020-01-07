package Exercise_04._07;

public class CornerPointCoordinates {
    public static void main(String[] args) { //github
        double perimeter = 100.52;

        // Prompt the user to enter the radius of the bounding circle
        System.out.print("The radius of the bounding circle: ");

//        https://math.stackexchange.com/questions/1990504/how-to-find-the-coordinates-of-the-vertices-of-a-pentagon-centered-at-the-origin
        // Compute the five angle of pentagon
        double angleB = 90 - (360 / 5);
        double angleC = angleB - 72;
        double angleE = 90 - (-360 / 5);
        double lengthOfSide = perimeter / 5;


    }
}
