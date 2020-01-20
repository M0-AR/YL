package Exercise_04._23;

import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {// y and how much comma in answer
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
        System.out.println( "Employee Name: " + name );
        System.out.println( "HoursWorked: " + hoursPerWeek );
        System.out.printf( "Pay Rate: $" + hourlyPayRate );
        System.out.println( "Gross Pay: " + hoursPerWeek * hourlyPayRate );
        System.out.println( "Deductions: " );
        System.out.println( "   Federal withholding (" + federalTax * 100 + "%): $" + (hourlyPayRate * hoursPerWeek) * 0.2 );
        System.out.println( "   State withholding (" + stateTax * 100 + "%): $" + (hourlyPayRate * hoursPerWeek) * 0.09 );
        double totalDeduction = ((hourlyPayRate * hoursPerWeek) * federalTax + (hourlyPayRate * hoursPerWeek) * stateTax);
        System.out.println( "   Total Deduction: $" + totalDeduction );
        System.out.println( "Net Pay: $" + ((hoursPerWeek * hourlyPayRate) - totalDeduction) );
    }
}
