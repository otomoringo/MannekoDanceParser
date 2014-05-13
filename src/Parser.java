import java.util.Scanner;
import java.util.Stack;

public class Parser {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			String line = sc.nextLine();
			if (line.equals("0"))
				break;
			ForParser(line);
		}
		sc.close();
	}

	private static void ForParser(String line) {
		Stack<Character> stack = new Stack<Character>();
		int count = 0;
		int roop_counter = 0;

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			switch (ch) {
			case '(':
				stack.push(ch);
				count = 0;
				break;

			case ')':
				i -= count;
				roop_counter--;
				break;

			default:
				if (stack.peek() == '(') {
					roop_counter = (int) ch;
					stack.pop();
				} else {
					System.out.println(ch);
				}
			}

		}
	}

	// case '[':
	// stack.push(ch);
	// // stack.empty();
	// // stack.pop();
	// break;
	//
	// if (stack.peek() == '[')
	// boolean frag = true;
	//
	// if(stack.peek()=='|')
	// frag == false;

}
