import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluatorulDeExpresii {

	private static boolean isOperator(char character) {
		if(character == '+' || character == '-' || character == '*' || character == '/' || character == '^') {
			return true;
		}   return false;
	}
	private static boolean isLeftAsociated(char character) {
		if(character == '^') {
			return false;
		}   return true;
	}
	private static int nivelPrecedenta(char op) {
		switch (op) {
		case '+':
		case '-':
			return 11;
		case '*':
		case '/':
			return 12;
		case '^':
			return 13;
		default:
			System.out.println("Operatorul nu este cunoscut: " + op);
			return -1;
		}
	}

	public static String toPostFix(String expression) {
		StringBuilder postFix = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<Character>();

		for(int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);

			if(Character.isDigit(character)) {
				postFix.append(character);

			} else if(isOperator(character)) {

				while(!stack.isEmpty() && isOperator(stack.peek()) && (stack.peek() != '(') && (nivelPrecedenta(character) < nivelPrecedenta(stack.peek())
						|| (nivelPrecedenta(character) == nivelPrecedenta(stack.peek()) && isLeftAsociated(stack.peek())))) {
					postFix.append(stack.pop());
				}
				stack.push(character);

			} else if(character == '(') {
				stack.push(character);

			} else if(character == ')') {

				while(stack.peek() != '(') {
					postFix.append(stack.pop());
				}
				if(stack.isEmpty()) {
					return "Expresia avea paranteze gresite.";
				}
				if(stack.peek() == '(') {
					stack.pop();
				}
			}
		}

		while(!stack.isEmpty() && isOperator(stack.peek())) {
			if(stack.peek() == '(') {
				return "Expresia avea paranteze gresite.";
			}
			postFix.append(stack.pop());
		}


		return postFix.toString();
	}

	public static int toCalculate(String expression) {
		Deque<Integer> stack = new ArrayDeque<Integer>();

		for(int i = 0; i < expression.length(); i++) {

			if(Character.isDigit(expression.charAt(i))) {
				stack.push(Character.getNumericValue(expression.charAt(i)));

			} else {
				int op1 = stack.pop();
				int op2 = stack.pop();
				switch (expression.charAt(i)) {
				case '+':
					stack.push(op2 + op1);
					break;
				case '-':
					stack.push(op2 - op1);
					break;
				case '*':
					stack.push(op2 * op1);
					break;
				case '/':
					stack.push(op2 / op1);
					break;
				case '^':
					stack.push((int) Math.pow(op2, op1));
					break;
				default:
					break;
				}
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {

		String expression = "3+(2+1)*2^3^2-8/(5-1*2/2)";
		String fixed = toPostFix(expression);
		System.out.println(fixed + "\n");
		System.out.println(toCalculate(fixed));

	}

}
