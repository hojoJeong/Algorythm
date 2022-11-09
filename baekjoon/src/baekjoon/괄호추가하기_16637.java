package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class ��ȣ�߰��ϱ�_16637 {
	static char[] arr;
	static int n, max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		String input = sc.next();
		arr = input.toCharArray();
		max = Integer.MIN_VALUE;   // 0 �ȵ�
		dfs(2, Integer.parseInt(Character.toString(arr[0]))); // == -'0'
		System.out.println(max);
	}
	static void dfs(int index, int curresult) {
		if(index >= n) {
			max = Math.max(curresult, max);
			return;
		}
		dfs(index+2, cal(curresult, arr[index-1], arr[index]-'0')); // ��ȣX  -> ��������� �հ� ���� ��(arr[index])�� ����(arr[index-1])
		if(index+2 < n) {
			int temp = cal(arr[index] - '0', arr[index+1], arr[index+2]-'0');
			dfs(index+4, cal(curresult, arr[index-1], temp));
		}
	}
	static int cal(int num1, char op, int num2) {
		if(op == '+') return num1 + num2;
		else if(op == '*') return num1 * num2;
		else return num1 - num2;
	}
}