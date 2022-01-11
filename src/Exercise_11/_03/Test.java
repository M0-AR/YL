package Exercise_11._03;

public class Test {
    // Main method
    public static void main(String[] args) {
        // Create Account, SavingsCAccount and Checking Account objects
        Account account = new Account(1, 20000);
        SavingsAccount savings = new SavingsAccount(2, 20000);
        CheckingAccount checking = new CheckingAccount(3, 20000, -2);

        // Set annual interest rate of 4.5%
        account.setAnnualInterestRate(4.5);
        savings.setAnnualInterestRate(4.5);
        checking.setAnnualInterestRate(4.5);

        // withdraw $2,500
        account.withdraw(2500);
        savings.withdraw(2500);
        checking.withdraw(2500);

        // Deposit $3,000
        account.deposit(3000);
        savings.deposit(3000);
        checking.deposit(3000);

        // Invoke toString methods
        System.out.println(account.toString());
        System.out.println(savings.toString());
        System.out.println(checking.toString());
    }
}
