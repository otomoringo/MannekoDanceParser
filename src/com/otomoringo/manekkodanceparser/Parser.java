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

			Stack<Util> stack = new Stack<Util>();
			Util namae = new Util('(', 3, 2);
			stack.push(namae);
			Util name = stack.peek();// 積んだてっぺんのクラスをとりあえずとりだす
			char c = name.bracket;// stack.peek().bracketでもよい

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
			switch (ch) {
			case '(':
				stack.push(ch);
				count = 0;
				break;

			case ')':
				if (loopCounter > 1) {
					i -= count + 1;
					loopCounter--;
					count = 0;
				}

				if (loopCounter <= 0) {
					stack.pop();
					stack.pop();
					if (!stack.empty() && stack.peek() != '0') {
						i -= count - 1;
						int l = Integer.parseInt("" + stack.pop()) - 1;
						stack.push((char) l);
					}
				}
				break;

			default:
				if (i != 0 && line.charAt(i - 1) == '(') {
					loopCounter = Integer.parseInt("" + ch);
					stack.push(ch);
				} else {
					if (loopCounter > 0 || stack.empty()) {
						result += ch;
						count++;
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
