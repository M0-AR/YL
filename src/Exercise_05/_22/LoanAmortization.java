package Exercise_05._22;
/*
(Financial application: loan amortization schedule) The monthly payment for a
given loan pays the principal and the interest. The monthly interest is computed
by multiplying the monthly interest rate and the balance (the remaining principal).
The principal paid for the month is therefore the monthly payment minus
the monthly interest. Write a program that lets the user enter the loan amount,
number of years, and interest rate and displays the amortization schedule for the
loan.
*/

import java.util.Scanner;

public class LoanAmortization {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        double loanAmount;
        double  interestRate;
        int numOfYears;

        System.out.print( "Loan Amount: " );
        loanAmount = input.nextDouble();
        System.out.print( "\nNumber of Years: " );
        numOfYears = input.nextInt();
        System.out.print( "\nAnnual Interest Rate: " );
        interestRate = input.nextDouble();

        double monthlyInterestRate = interestRate / 1200;
        double monthlyRepayment = loanAmount * monthlyInterestRate /
                (1-(Math.pow( 1/ (1+monthlyInterestRate), numOfYears*12 )));

        double balance = loanAmount;
        double interest;
        double principal;

        System.out.println( "\nMonthly Payment: "  + (int) (monthlyRepayment * 100) / 100.0);
        System.out.println( "Total Payment: " + (int)(monthlyRepayment * 12 * numOfYears * 100) / 100.0 + "\n");

        System.out.println("Payment!\tInterest\tPrincipal\tBalance");
        for (int i = 1; i <=numOfYears * 12; i++) {
            interest = (int) (monthlyInterestRate * balance * 100) / 100.0;
            principal = (int) ((monthlyRepayment - interest )* 100) / 100.0;
            balance = (int) ((balance - principal )* 100) / 100.0;
            System.out.println(i + "\t\t" + interest + "\t\t" + principal + "\t\t" + balance);

        }


    }
}
