package swea;

import java.io.*;
import java.util.*;

public class swea_0812_°è»ê±â {
	static int len;

	static void solve(String input) {
		String output = "";
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < len; i++) {
			char index = input.charAt(i);
			if (index == '(')
				stack.push(index);
			else if (index >= '0' && index <= '9')
				output += index;
			else if (index == '*' || index == '+') {
				if (index == '+' && (stack.peek() == '*' || stack.peek() == '+'))
					output += stack.pop();
				if (index == '*' && stack.peek() == '*')
					output += stack.pop();
				stack.push(index);
			} else if (index == ')') {
				while (true) {
					char temp = stack.pop();
					if (temp == '(')
						break;
					output += temp;
				}
			}
		}
		while (!stack.isEmpty())
			output += stack.pop();
		calc(output);
	}

	static void calc(String input) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int index = 0; index < input.length(); index++) {
			char value = input.charAt(index);
			if (value == '*')
				stack.push(stack.pop() * stack.pop());
			else if (value == '+')
				stack.push(stack.pop() + stack.pop());
			else
				stack.push(Integer.parseInt(value + ""));
		}
		System.out.println(stack.pop());
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		for (int t = 1; t <= 10; t++) {
			len = sc.nextInt();
			input = sc.next();
			System.out.print("#" + t + " ");
			solve(input);
		}
	}
}