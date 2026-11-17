package Exercise_20._23;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Exercise_20_23 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = input.nextLine();
        System.out.println(expression + " = " + evaluate(expression));
    }

    public static double evaluate(String expression) {
        Deque<Double> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        char[] tokens = expression.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (token == ' ') {
                continue;
            }
            if (Character.isDigit(token) || token == '.') {
                StringBuilder number = new StringBuilder();
                while (i < tokens.length
                        && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    number.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(number.toString()));
            } else if (token == '(') {
                operators.push(token);
            } else if (token == ')') {
                while (operators.peek() != '(') {
                    apply(values, operators.pop());
                }
                operators.pop();
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(token)) {
                    apply(values, operators.pop());
                }
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) {
            apply(values, operators.pop());
        }
        return values.pop();
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/'
                || token == '%' || token == '^';
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/', '%' -> 2;
            default -> 1;
        };
    }

    private static void apply(Deque<Double> values, char operator) {
        double second = values.pop();
        double first = values.pop();
        double result = switch (operator) {
            case '+' -> first + second;
            case '-' -> first - second;
            case '*' -> first * second;
            case '/' -> first / second;
            case '%' -> first % second;
            case '^' -> Math.pow(first, second);
            default -> throw new IllegalArgumentException("Unknown operator " + operator);
        };
        values.push(result);
    }
}
