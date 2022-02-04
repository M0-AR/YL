package Exercise_11._08;
/***************************************
 *              Account
 ***************************************
 * -id: int
 * -name: String
 * -balance: double
 * -annalInterestRate: double
 * -dateCreated: Date
 * -transactions: ArrayList<Transaction>
 *------------------------------------------
 * // todo
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Account {
    private int id;
    private String name;
    private double balance;
    private static double annualInterestRate;
    private Date dateCreated;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Constructors
    /** Creates a default account */
    public Account() {
        this.dateCreated = new Date();
    }

    /** Creates an account with specified id, name, and initial balance */
    public Account(int id, String name, double newBalance) {
        this.id = id;
        this.name = name;
        this.balance = newBalance;
        this.dateCreated = new Date();
    }

    // Mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // Method
    /** Return monthly interest */
    public double getMonthlyInterest() {
        return balance * (annualInterestRate / 1200);
    }

    /** Decrease balance by amount */
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Don't have enough money to withdraw.");
            return;
        }
        this.balance -= amount;
        this.transactions.add(new Transaction('W', amount, this.balance, ""));
    }

    /** Increase balance by amount */
    public void deposit(double amount) {
        this.balance += amount;
        this.transactions.add(new Transaction('D', amount, this.balance, ""));
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", interestRate=" + annualInterestRate +
                ", transactions=" + Arrays.toString(transactions.toArray()) +
                '}';
    }
}
