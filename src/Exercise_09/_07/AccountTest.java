package Exercise_09._07;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account( 1122, 20000 );
        account.setAnnualInterestRate( 0.045 );
        account.withdraw( 2500 );
        account.deposit( 3000 );
        System.out.println("\nAccount state: ");
        System.out.println("\nBalance: " + account.getBalance() +
                            "\nMonthly interest " + account.getMonthlyInterest() +
                            "\nDate when this account was created: " + account.getDateCreated());
    }
}
