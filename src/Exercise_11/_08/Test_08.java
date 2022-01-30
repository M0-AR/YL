package Exercise_11._08;

public class Test_08 {
    public static void main(String[] args) {
        Account account = new Account(1122, "George", 1000, 1.5);

        account.deposit(30);
        account.deposit(40);
        account.deposit(50);

        account.withdraw(5, "");
        account.withdraw(4, "");
        account.withdraw(2, "");

        // Print object As JSON form
        //System.out.println(account);

        System.out.println();

        // Print object from here
        System.out.println("Name: " + account.getName());
        System.out.println("Annual Interest Rate: " + account.getInterestRate());
        System.out.println("Balance: " + account.getBalance());
        System.out.println();

        System.out.printf("%-35s%-15s%-15s%-15s", "Date", "Type", "Amount", "Balance");
        for (Transaction t : account.getTransactions()) {
            System.out.println();
            System.out.printf("%-35s%-15s%-15s%-15s", t.getDate(), t.getType(), t.getAmount(), t.getBalance());
        }
    }
}
