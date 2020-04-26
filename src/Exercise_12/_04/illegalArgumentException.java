package Exercise_12._04;
/*********************************************************************************
 * (IllegalArgumentException) Modify the Loan class in Listing 10.2 to throw      *
 * IllegalArgumentException if the loan amount, interest rate, or number of years *
 * is less than or equal to zero.                                                 *
 *********************************************************************************/
import java.util.Scanner;

public class illegalArgumentException {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );
        boolean continueInput = true;


        do {
            // Enter annual interest rate
            System.out.print(
                    "Enter annual interest rate, for example, 8.25: ");
            double annualInterestRate = input.nextDouble();

            // Enter number of years
            System.out.print("Enter number of years as an integer: ");
            int numberOfYears = input.nextInt();

            // Enter loan amount
            System.out.print("Enter loan amount, for example, 120000.95: ");
            double loanAmount = input.nextDouble();


            try {
                // Create a Loan object
                NewLoan loan =
                        new NewLoan(annualInterestRate, numberOfYears, loanAmount);
                continueInput = false;

                // Display loan date, monthly payment, and total payment
                System.out.printf("The loan was created on %s\n" +
                                "The monthly payment is %.2f\nTne total payment is %.2f\n",
                        loan.getLoanDate().toString(), loan.monthlyPayment(),
                        loan.totalPayment());
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } while (continueInput);
        System.out.println("End of program");
    }


}



class NewLoan {
    private double annualInterestRate;
    private int numOfYears;
    private double loanAmount;
    private java.util.Date loanDate;


    /** Default constructor */
    public NewLoan() {
        this(7.5, 30, 100000);
    }

    /** Construct a NewLoan with specified annual interest rate,
     number of years and loan amount
     */
    public NewLoan(double annualInterestRate, int numOfYears,
                   double loanAmount) {
        if (annualInterestRate <= 0)
            throw new IllegalArgumentException("Annual interest rate must be positive.");
        if (numOfYears <= 0)
            throw new IllegalArgumentException("Number of years must be positive.");
        if (loanAmount <= 0)
            throw new IllegalArgumentException("Loan amount must be positive.");
        setAnnualInterestRate(annualInterestRate);
        setNumOfYears(numOfYears);
        setLoanAmount(loanAmount);
    }

    /** Return annualInterestRate */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /** Set a new annualInterestRate */
    public void setAnnualInterestRate(double annualInterestRate) {
        if (annualInterestRate <= 0)
            throw new IllegalArgumentException("Annual interest rate must be positive.");
        this.annualInterestRate = annualInterestRate;
    }

    /** Return numOfYears */
    public int getNumOfYears() {
        return numOfYears;
    }

    /** Set a new numOfYears */
    public void setNumOfYears(int numOfYears) {
        if (numOfYears <= 0)
            throw new IllegalArgumentException("Number of years must be positive.");
        this.numOfYears = numOfYears;
    }

    /** Return loanAmount */
    public double getLoanAmount() {
        return loanAmount;
    }

    /** Set a newloanAmount */
    public void setLoanAmount(double loanAmount) {
        if (loanAmount <= 0)
            throw new IllegalArgumentException("Loan amount must be positive.");
        this.loanAmount = loanAmount;
    }

    /** Find monthly payment */
    public double monthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), numOfYears * 12)));
    }

    /** Find total payment */
    public double totalPayment() {
        return monthlyPayment() * numOfYears * 12;
    }

    /** Return loan date */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}

