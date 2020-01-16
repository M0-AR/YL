package Exercise_04._23;

import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter employee's information
        System.out.println("Enter employee's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter number of hours forked in a week: ");
        double hoursPerWeek = scanner.nextDouble();
        System.out.println("Enter hourly pay rate: ");
        double hourlyPayRate = scanner.nextDouble();
        System.out.println("Enter federal tax withholding rate: ");
        double federalTax = scanner.nextDouble();
        System.out.println("Enter state tax withholding rate: ");
        double stateTax = scanner.nextDouble();

        // Compute
    }
}
