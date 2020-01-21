package Exercise_04._23;
/*
(Financial application: payroll) Write a program that reads the following information
and prints a payroll statement:
Employee’s name (e.g., Smith)
Number of hours worked in a week (e.g., 10)
Hourly pay rate (e.g., 9.75)
Federal tax withholding rate (e.g., 20%)
State tax withholding rate (e.g., 9%)
*/

import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter employee's information
        System.out.print( "Enter employee's name: " );
        String name = scanner.nextLine();
        System.out.print( "Enter number of hours forked in a week: " );
        double hoursPerWeek = scanner.nextDouble();
        System.out.print( "Enter hourly pay rate: " );
        double hourlyPayRate = scanner.nextDouble();
        System.out.print( "Enter federal tax withholding rate: " );
        double federalTax = scanner.nextDouble();
        System.out.print( "Enter state tax withholding rate: " );
        double stateTax = scanner.nextDouble();

        // Compute and display the result
        double grossPay, federal, state, totalDeduction;
        System.out.println(
                "Employee Name: " + name +
                        "\nHoursWorked: " + hoursPerWeek +
                        "\nPay Rate: $" + hourlyPayRate +
                        "\nGross PAy: " + (grossPay = hoursPerWeek * hourlyPayRate) +
                        "\nDeductions:\n    Federal withholding (" + federalTax * 100 + "%): $" +
                        (federal = grossPay * federalTax) +
                        "\n    State withholding (" + stateTax * 100 + "%): $" +
                        (state = grossPay * stateTax) +
                        "\n    Total Deduction: $" + (totalDeduction = federal + state)+
                        "Net Pay: $" + (grossPay - totalDeduction)
        );
    }
}
