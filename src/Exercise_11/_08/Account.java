package Exercise_11._08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Account {
    private int id;
    private String name;
    private double balance;
    private double interestRate;
    private Date dateCreated;
    private ArrayList<Transaction> transactions;

    public Account(int id, String name, double newBalance, double newInterestRate) {
        this.id = id;
        this.name = name;
        this.balance = newBalance;
        this.interestRate = newInterestRate;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void withdraw(double amount, String description) {
        if (amount > balance) {
            System.out.println("Don't have enough money to withdraw.");
            return;
        }
        this.balance -= amount;
        this.transactions.add(new Transaction('W', amount, this.balance, description));
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
                ", interestRate=" + interestRate +
                ", transactions=" + Arrays.toString(transactions.toArray()) +
                '}';
    }
}
