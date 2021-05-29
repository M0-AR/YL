package Exercise_09._07;

public class AccountTest {
    public static void main(String[] args) {
        // Create an Account object with an account
        // ID of 1122, and a balance of $20,000
        Account account = new Account( 1122, 20000 );

        // Set annual interest rate of 4.5%
        account.setAnnualInterestRate( 0.045 );

        // Withdraw $2,500
        account.withdraw( 2500 );

        // Deposit $3,0000
        account.deposit( 3000 );

        // Display the balance , the monthly interest,
        // and the date when this account was created.
        System.out.println("\n\t\tAccount State");
        System.out.println("----------------------------------");
        System.out.println("Account ID: " + account.getId());
        System.out.println("\nBalance: " + account.getBalance() +
                            "\nMonthly interest " + account.getMonthlyInterest() +
                            "\nDate when this account was created: " + account.getDateCreated());
    }
}
