package Exercise_05._32;

import javafx.beans.property.IntegerProperty;

public class LotteryGame {
    public static void main(String[] args) {
        // Generate to digit number
        int n1 = (int)(Math.random() * 10) + 1;
        int n2 = (int)(Math.random() * 10) + 1;

        // Change n2 if n1 == n2
        while (n1 == n2){
            n2 = (int)(Math.random() * 10) + 1;
        }

        // Display result
        System.out.println("First Digit; " + n1 + "\nSecond Digit: " + n2);
    }
}
