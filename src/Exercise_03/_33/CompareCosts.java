package Exercise_03._33;

import java.util.Scanner;

public class CompareCosts {
    public static void main(String[] args) { // github output repeating
        Scanner scan = new Scanner( System.in );

        // Prompt the user to enter weight and price
        System.out.print("Enter weight and price for package 1: ");
        double price1 = scan.nextDouble(), weight1 = scan.nextDouble();

        System.out.print("Enter weight and price for package 2: ");
        double price2 = scan.nextDouble(), weight2 = scan.nextDouble();

        // Calculate the price of one kg of packages
        double package1 = weight1 / price1;
        double package2 = weight2 / price2;

        // Display the result
        System.out.println("Package " + ((package2 < package1) ? "2":
           (package2 > package1) ? "1": "Two packages have the same price.") + " has a better price.");

    }
}
