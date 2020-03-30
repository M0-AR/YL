package BookExercise._32;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**To avoid the problem with output use synchronized in method or in run method*/
public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("What is balance? " + account.getBalance());
    }

    // A thread for adding a penny to the account
    private static class AddAPennyTask implements Runnable {
        public void run() {
//            account.deposit(1);
            /** Synchronized:   If the object is already locked by
             another thread, the thread is blocked until the lock is released */
            synchronized (account) {
                account.deposit(1);
            }
        }
    }

    // An inner class for account
    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        /** Synchronized the method mean there is only one thread can have an access to the method*/
        // public synchronized void deposit(int amount) { to fix the problem

        /** Multiple threads have an access to this method */
        public  void deposit(int amount) {
            int newBalance = balance + amount;


            // This delay is deliberately added to magnify the
            // data-corruption problem and make it easy to see.
            try {

                Thread.sleep(5);

            }
            catch (InterruptedException ex) {
            }


            balance = newBalance;
        }
    }
}
