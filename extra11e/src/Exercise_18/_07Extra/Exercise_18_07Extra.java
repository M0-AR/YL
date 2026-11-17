package Exercise_18._07Extra;

import java.util.Scanner;

public class Exercise_18_07Extra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an expression: ");
		String expression = input.nextLine();

		System.out.println(expression + " = " + evaluateExpression(expression));
	}

	public static double evaluateExpression(String expression) {
		String exp = expression.trim();

		int index = lastOperator(exp, "+-");
		if (index > 0) {
			char operator = exp.charAt(index);
			double left = evaluateExpression(exp.substring(0, index));
			double right = evaluateExpression(exp.substring(index + 1));
			return operator == '+' ? left + right : left - right;
		}

		index = lastOperator(exp, "*/");
		if (index > 0) {
			char operator = exp.charAt(index);
			double left = evaluateExpression(exp.substring(0, index));
			double right = evaluateExpression(exp.substring(index + 1));
			return operator == '*' ? left * right : left / right;
		}

		return Double.parseDouble(exp);
	}

	private static int lastOperator(String expression, String operators) {
		for (int i = expression.length() - 1; i > 0; i--) {
			if (operators.indexOf(expression.charAt(i)) >= 0) {
				return i;
			}
		}
		return -1;
	}
}
