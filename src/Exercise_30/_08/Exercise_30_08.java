package Exercise_30._08;

public class Exercise_30_08 {
    private static final Account account = new Account();

    public static void main(String[] args) throws InterruptedException {
        Thread deposit = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.deposit(10);
            }
        });
        Thread withdraw = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.withdraw(10);
            }
        });
        deposit.start();
        withdraw.start();
        deposit.join();
        withdraw.join();
        System.out.println("Final balance: " + account.getBalance());
        System.out.println("No overdraft occurred: " + account.isSafe());
    }

    private static class Account {
        private double balance;
        private boolean safe = true;

        public synchronized void deposit(double amount) {
            balance += amount;
            notifyAll();
        }

        public synchronized void withdraw(double amount) {
            while (balance < amount) {
                try {
                    wait();
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            balance -= amount;
            if (balance < 0) {
                safe = false;
            }
        }

        public synchronized double getBalance() {
            return balance;
        }

        public synchronized boolean isSafe() {
            return safe;
        }
    }
}
