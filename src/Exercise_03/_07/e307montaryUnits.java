package Exercise_03._07;

import java.util.Scanner;

public class e307montaryUnits { // done
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //1
        boolean isDollars;
        double dollars;
        int numberOfDollars, pennies;

        //2
        dollars = 0;

        do {
            // Prompt the user to enter amount of dollars
            System.out.println("Enter the amount of dollars: ");
            if(input.hasNextDouble() && (dollars >= 0)){
                isDollars = true;
                dollars = input.nextDouble();
                if(dollars < 0){
                    System.out.println("Please make sure that you input a valid number");
                    isDollars = false;

                }

            }else{
                System.out.println("Please make sure that you input a valid number");
                isDollars = false;
                // To avoid infinite loop
                input.next();

            }
        }while (!(isDollars));


        //3
        // Convert dollars to pennies
        pennies = (int) (dollars * 100);

        // Compute the number of dollars
        numberOfDollars = pennies / 100;
        // Compute the number of pennies
        pennies %=  100;

        //4
        if (numberOfDollars > 1 && pennies > 1) {
            System.out.println(numberOfDollars + " dollars and " + pennies + " pennies");
        }else if (numberOfDollars > 1 && pennies == 1) {
            System.out.println(numberOfDollars + " dollars and " + pennies + " penny");
        }else if (numberOfDollars == 1 && pennies > 1){
            System.out.println(numberOfDollars + " dollar and " + pennies + " pennies");
        }else {
            System.out.println(numberOfDollars + " dollar and " + pennies + " penny");

        }


    }
}