package Exercise_10._07;

import Exercise_09._07.Account;

import java.util.Scanner;

public class ATMMachineGame {
    public static void main(String[] args) {
        Account[] accounts = new Account[10];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100);
        }

        Scanner input = new Scanner( System.in );

        int id;
        int choice = 0;

        System.out.print("Enter an id: ");
        id = input.nextInt();

        while (true) {
            System.out.println();
            System.out.println("Main menu");
            System.out.println("1: check balance");
            System.out.println("2: withdraw");
            System.out.println("3: deposit");
            System.out.println("4: exit");

            System.out.print("Enter a choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("The balance is " +
                                            accounts[id].getBalance());
                    break;
                case 2:
                    System.out.print("Enter an amount to withdraw: ");
                    int withdraw = input.nextInt();
                    accounts[id].withdraw( withdraw );
                    break;
                case 3:
                    System.out.print("Enter an amount to deposit: ");
                    int deposit = input.nextInt();
                    accounts[id].deposit( deposit );
                    break;
                case 4:
                    System.exit( 1 );
                default:
                    System.out.println("Wrong input, please enter a valid number from the menu");
            }
        }
    }


    public void displayMenu() {

    }
}
