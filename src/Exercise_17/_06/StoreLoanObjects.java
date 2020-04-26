package Exercise_17._06;

import java.io.*;

public class StoreLoanObjects { // github and yl // todo remember Serializable id L
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        // Default information
        double annualInterestRate = 1;
        int numberOfYears = 1;
        double loanAmount = 1001;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream( "Exercise17_06.dat" ) )
        ) {
            // Create five object and store them in Exercise17_06.dat
            for (int i = 0; i < 5; i++) {
                Loan loan = new Loan
                        ( annualInterestRate++, numberOfYears++, loanAmount++ );
                outputStream.writeObject( loan );
            }
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream( "Exercise17_06.dat" ) )
        ){
            while (true){
                Loan loan = (Loan) objectInputStream.readObject();
                System.out.println(loan);
                System.out.printf("Total loan amount: $%.2f\n",
                        loan.getTotalPayment());
                System.out.println();
            }
        } catch (EOFException e) {
            // Use EOFException to end the loop
        }


    }
}

class Loan implements Serializable {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    /**
     * Default constructor
     */
    public Loan() {
        this( 2.5, 1, 1000 );
    }

    /**
     * Construct a loan with specified annual interest rate,
     * number of years and loan amount
     */
    public Loan(double annualInterestRate, int numberOfYears,
                double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }

    /**
     * Return annualInterestRate
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * Set a new annualInterestRate
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Return numberOfYears
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * Set a new numberOfYears
     */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * Return loanAmount
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * Set a newloanAmount
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Find monthly payment
     */
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow( 1 / (1 + monthlyInterestRate), numberOfYears * 12 )));
        return monthlyPayment;
    }

    /**
     * Find total payment
     */
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }

    /**
     * Return loan date
     */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}
