package Exercise_05._22;

import java.util.Scanner;

public class LoanAmortization {
    public static void main(String[] args) { // github and g
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

        for (int i = 1; i <=numOfYears ; i++) {
            interest = (int) (monthlyInterestRate * balance * 100) / 100.0;
            principal = (int) ((monthlyInterestRate - interest )* 100) / 100.0;
            balance = (int) ((balance - principal )* 100) / 100.0;
            System.out.println(i + "\t\t" + interest + "\t\t" + principal + "\t\t" + balance);

        }


    }
}
