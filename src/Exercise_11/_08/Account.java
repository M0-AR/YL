package Exercise_11._08;

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

    public Account() {
        this.dateCreated = new Date();
    }

    public Account(int id, String name, double newBalance) {
        this.id = id;
        this.name = name;
        this.balance = newBalance;
        this.dateCreated = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getMonthlyInterest() {
        return balance * (annualInterestRate / 1200);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Don't have enough money to withdraw.");
            return;
        }
        this.balance -= amount;
        this.transactions.add(new Transaction('W', amount, this.balance, ""));
    }

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
