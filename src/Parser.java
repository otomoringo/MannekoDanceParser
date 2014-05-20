import java.util.Scanner;
import java.util.Stack;

public class Parser {
	private int _branch;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Parser parser = new Parser();
		while (true) {
			String line = sc.nextLine();
			if (line.equals("0"))
				break;
			String result = parser.parse(line);
			System.out.println(result);
		}
		sc.close();
	}

	public String parse(String line) {
		String result = "";
		Stack<Character> stack = new Stack<Character>();
		int count = 0;
		int loopCounter = 1;

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (loopCounter == 0 && stack.empty())
				break;
			switch (ch) {
			case '(':
				stack.push(ch);
				count = 0;
				break;

			case ')':
				i -= count + 1;
				loopCounter--;
				count = 0;
				break;

			default:
				if (!stack.empty() && stack.peek() == '(') {
					loopCounter = Integer.parseInt("" + ch);
					stack.pop();
				} else {
					result += ch;
					count++;
				}
				break;
			}
		}
		return result;
	}

	public String parseIf(String line) {
		String result = "";
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			switch (ch) {
			case '[':
				stack.push(ch);
				break;

			case '|':
				stack.pop();
				stack.push(ch);
				break;

			case ']':
				stack.pop();
				break;

			default:
				if (getBranch() == 1 && stack.peek() == '[') {
					result += ch;
				} else if (getBranch() == 0 && stack.peek() == '|') {
					result += ch;
				}
				break;
			}
		}
		return result;
	}

	public int getBranch() {
		return _branch;
	}

	public void setBranch(int branch) {
		_branch = branch;
	}

}
