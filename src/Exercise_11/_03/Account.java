package Exercise_11._03;
/********************************************
 *               Account                    *
 *------------------------------------------*
 * -id: int                                 *
 * -balance: double                         *
 * -annualInterestRate: double              *
 * -dateCreated: Date                       *
 *------------------------------------------*
 * +getMonthlyInterestRate(): double        *
 * +getMonthlyInterest(): double            *
 * +withdraw(amount: double): void          *
 * +deposit(amount: double); void           *
 *------------------------------------------*/
import java.util.Date;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account() {
        this.dateCreated = new Date();
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public double getMonthlyInterestRate() {
        return (this.balance * (this.annualInterestRate / 12)) / 100;
    }

    public double getMonthlyInterest() {
        return this.balance * (this.annualInterestRate / 12);
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}