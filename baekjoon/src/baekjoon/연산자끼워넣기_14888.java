package baekjoon;

import java.util.*;
import java.io.*;

public class 연산자끼워넣기_14888 {

	static int[] num;
	static int[] op;
	static char[] calop;
	static boolean[] pvis;
	static int n, max, min;
	static ArrayList<Character> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		num = new int[n];
		op = new int[4];
		list = new ArrayList<>();
		calop = new char[n - 1];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < op[i]; j++) {
				if (i == 0)
					list.add('+');
				else if (i == 1)
					list.add('-');
				else if (i == 2)
					list.add('*');
				else if (i == 3)
					list.add('/');
			}
		}

		pvis = new boolean[n];
		per(0, n - 1, n - 1);

		System.out.println(max);
		System.out.println(min);

	}

	static void per(int index, int n, int m) {
		if (index == m) {
			cal();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (pvis[i] == false) {
				calop[index] = list.get(i);
				pvis[i] = true;
				per(index + 1, n, m);
				pvis[i] = false;
			}
		}
	}

	static void cal() {
		int[] temp = new int[num.length];
		for (int i = 0; i < num.length; i++) {
			temp[i] = num[i];
		}
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			if (calop[i] == '+')
				temp[i + 1] = temp[i] + temp[i + 1];
			else if (calop[i] == '-')
				temp[i + 1] = temp[i] - temp[i + 1];
			else if (calop[i] == '*')
				temp[i + 1] = temp[i] * temp[i + 1];
			else if (calop[i] == '/')
				temp[i + 1] = temp[i] / temp[i + 1];
			result = temp[i + 1];
		}
		min = Math.min(min, result);
		max = Math.max(max, result);
	}
}
