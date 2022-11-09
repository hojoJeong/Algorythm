package swea;

import java.util.*;
import java.io.*;

public class swea_1232_사칙연산 {

	static ArrayList<ArrayList<Character>> list;
	static int ans;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			list = new ArrayList<>();

			int n = Integer.parseInt(br.readLine());

			for (int i = 0; i < n+1; i++) {
				list.add(new ArrayList<>());
			}
			for (int i = 1; i <= n / 2; i++) {
				st = new StringTokenizer(br.readLine());

				st.nextToken();
				char op = st.nextToken().charAt(0);
				char child1 = st.nextToken().charAt(0);
				char child2 = st.nextToken().charAt(0);

				list.get(i).add(op);
				list.get(i).add(child1);
				list.get(i).add(child2);
			}
			for (int i = n / 2 + 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());

				st.nextToken();
				char child = st.nextToken().charAt(0);

				list.get(i).add(child);
			}
			
			Stack<Integer> numstack = new Stack<>();
			Stack<Character> opstack = new Stack<>();
			
			for (int i = 1; i <= n; i++) {
				if (list.get(i).get(0) < '0' || list.get(i).get(0) > '9') {
					opstack.add(list.get(i).get(0));
				}
				else if (list.get(i).get(0) >= '0' && list.get(i).get(0) <= '9') {
					numstack.add(list.get(i).get(0) - '0');
				}
			}
			ans = 0;
			dfs(numstack, opstack);
		}

	}

	static void dfs(Stack numstack, Stack opstack) {
		Stack<Integer> nextnumstack = new Stack<>();

		if(opstack.size() == 1) {
			int num1 = (int)numstack.pop();
			int num2 = (int)numstack.pop();
			char op = (char)opstack.pop();
			
			if(op == '+') ans = num1 + num2;
			else if(op == '-') ans = num1 - num2;
			else if(op == '*') ans = num1 * num2;
			else if(op == '/') ans = num1 / num2;
					
			return;
		}
				
		while(numstack.size() > 1){
			int num1 = (int) numstack.pop();
			int num2 = (int) numstack.pop();
			char op = (char) opstack.pop();
			
			if(op == '+') nextnumstack.add(num1 + num2);
			else if(op == '-') nextnumstack.add(num1 - num2);
			else if(op == '*') nextnumstack.add(num1 * num2);
			else if(op == '/') nextnumstack.add(num1 / num2);
		}
		if(numstack.size() != 0) nextnumstack.add((int)numstack.pop());
		
		dfs(nextnumstack, opstack);
	}
}
