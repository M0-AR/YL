package Exercise_11._03;
/***********************************
 *          SavingsAccount         *
 ***********************************
 * +SavingsAccount()               *
 * +withdraw(amount: double): void *
 ***********************************/
public class SavingsAccount extends Account{

    /** Construct a default CheckingAccount object */
    public SavingsAccount() {
        super();
    }

    /** Construct a SavingsAccount with specified id, balance */
    public SavingsAccount(int id, int balance) {
        super(id, balance);
    }

    /** Decrease balance by amount */
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            setBalance(getBalance() - amount);
        } else {
            System.out.println("Error! amount is overdrawn, transaction is rejected.");
        }
    }

}
