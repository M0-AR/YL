package Exercise_10._26;
/*************************************************************************************
 * (Calculator) Revise Listing 7.9, Calculator.java, to accept an expression as a    *
 * string in which the operands and operator are separated by zero or more spaces.   *
 * For example, 3+4 and 3 + 4 are acceptable expressions. Here is a sample run:      *
 *      - "4+5"                                                                      *
 *      - "4 + 5"                                                                    *
 *      - "4 +         5"                                                            *
 *      - "4 *         5"                                                            *
 ************************************************************************************/
// Todo: Make the program take an input from the command line.
import java.util.HashSet;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get an input from user
        String userInput = sc.nextLine();

        // Find numbers and operator
        int[] numbers = getNumbers(userInput);
        char operator = getOperator(userInput);

        // Calculate result
        int result = calculate(numbers, operator);

        // Display result
        System.out.println(numbers[0] + " " + operator + " " +
                                    numbers[1] + " = " + result);
    }

    private static int calculate(int[] numbers, char operator) {
        switch (operator) {
            case '+': return sumNumbers(numbers);
            case '-': return subtractNumbers(numbers);
            case '/': return divideNumbers(numbers);
            case '*': return multiplyNumbers(numbers);
        }
        return 0;
    }


    private static int[] getNumbers(String s) {
        int[] numbers = new int[2];
        int numbersIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                numbers[numbersIndex++] = Integer.parseInt(s.charAt(i) + "");
            }
        }
        return numbers;
    }

    private static char getOperator(String s) {
        HashSet<Character> operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('/');
        operators.add('*');

        for (int i = 0; i < s.length(); i++) {
            if (operators.contains(s.charAt(i))) {
                return s.charAt(i);
            }
        }
        return '?';
    }

    private static int sumNumbers(int[] numbers) {
        return numbers[0] + numbers[1];
    }

    private static int subtractNumbers(int[] numbers) {
        return numbers[0] - numbers[1];
    }

    private static int divideNumbers(int[] numbers) {
        return numbers[0] / numbers[1];
    }

    private static int multiplyNumbers(int[] numbers) {
        return numbers[0] * numbers[1];
    }
}
