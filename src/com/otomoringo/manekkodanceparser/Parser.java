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
		// Util util = stack.peek();// 積んだてっぺんのクラスをとりあえずとりだす
		// char c = util.bracket;// stack.peek().bracketでもよい

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			switch (ch) {
			case '(':
				util.setBracket(ch);
				util.setBackCounter(0);
				util.setLoopCounter(1);
				stack.push(util);
				break;

			case ')':
				if (util.getLoopCounter() > 1) {
					i -= util.getBackCounter() + 1;
					util.setLoopCounter(util.getLoopCounter() - 1);
					util.setBackCounter(0);
				}

				if (util.getLoopCounter() <= 0) {
					if (!stack.empty() && util.getLoopCounter() != 0) {
						i -= util.getBackCounter() - 1;
					}
					stack.pop();
				}

				break;

			default:
				if (i != 0 && line.charAt(i - 1) == '(') {
					util.setLoopCounter(Integer.parseInt("" + ch));
				} else if (util.getLoopCounter() > 0 || stack.empty()) {
					result += ch;
					util.setBackCounter(util.getBackCounter() + 1);
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
