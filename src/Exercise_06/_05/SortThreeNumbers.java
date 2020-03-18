package Exercise_06._05;

public class SortThreeNumbers {
    public static void main(String[] args) {// Github and y
        displaySortedNumbers( 2, 1, 3 );
    }

    public static void displaySortedNumbers(double num1, double num2, double num3){
        double temp = 0;
        if (num1 > num2){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        if (num2 > num3){
            temp = num2;
            num2 = num3;
            num3 = temp;
        }

        if (num1 > num2){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        System.out.println(num1 + " " + num2 + " " + num3);

    }


}
