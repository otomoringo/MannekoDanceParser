package com.otomoringo.manekkodanceparser;

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
		Stack<Util> stack = new Stack<Util>();
		Util util = new Util();
		// stack.push(util);
		// Util util = stack.peek();// 積んだてっぺんのクラスをとりあえずとりだす
		// char c = util.bracket;// stack.peek().bracketでもよい

		// Stack<Character> stack = new Stack<Character>();
		// int count = 0;
		// int loopCounter = 1;

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			switch (ch) {
			case '(':
				util.setBracket(ch);
				util.setBackCounter(0);
				stack.push(util);
				break;

			case ')':
				if (stack.peek().getLoopCounter() > 1) {
					i -= stack.peek().getBackCounter() + 1;
					util.setLoopCounter(util.getLoopCounter() - 1);
					util.setBackCounter(0);
				}

				if (stack.peek().getLoopCounter() <= 0) {
					stack.pop();
					if (!stack.empty() && stack.peek().getLoopCounter() != '0') {
						i -= stack.peek().getBackCounter() - 1;
						int l = Integer.parseInt(""
								+ stack.peek().getLoopCounter()) - 1;
						stack.push(util);
					}
				}
				break;

			default:
				if (i != 0 && line.charAt(i - 1) == '(') {
					util.setLoopCounter(Integer.parseInt("" + ch));
					stack.push(util);
				} else {
					if (util.getLoopCounter() > 0 || stack.empty()) {
						result += ch;
						util.setLoopCounter(util.getLoopCounter() + 1);
					}
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
				if (getBranch() == 0 && stack.peek() == '[') {
					result += ch;
				} else if (!(getBranch() == 0) && stack.peek() == '|') {
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
