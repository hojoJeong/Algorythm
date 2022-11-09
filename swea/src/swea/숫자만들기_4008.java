package swea;

import java.util.*;
import java.io.*;

public class 숫자만들기_4008 {
	static int[] op;
	static int[] num;
	static String[] opResult;
	static int ans, min, max, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {

			N = Integer.parseInt(br.readLine());
			op = new int[4];
			num = new int[N];
			opResult = new String[N-1];	
			ans = 0;
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	static void dfs(int index) {
		if(index == N-1) {
			int result = cal(opResult);
			min = Math.min(min, result);
			max = Math.max(max, result);
			ans = max - min;
			return;
		}
		
		if(op[0] > 0) {
			opResult[index] = "+";
			op[0]--;
			dfs(index+1);
			op[0]++;
		}
		if(op[1] > 0) {
			opResult[index] = "-";
			op[1]--;
			dfs(index+1);
			op[1]++;
		}
		if(op[2] > 0) {
			opResult[index] = "*";
			op[2]--;
			dfs(index+1);
			op[2]++;
		}
		if(op[3] > 0) {
			opResult[index] = "/";
			op[3]--;
			dfs(index+1);
			op[3]++;
		}
	}
	
	static int cal(String[] oper) {
		int result = num[0];
		int index = 0;
		for(int i=1; i<N; i++) {
			if(oper[index].equals("+")) {
				result = result + num[i];
			} else if(oper[index].equals("-")) {
				result = result - num[i];
			} else if(oper[index].equals("*")) {
				result = result * num[i];
			} else if(oper[index].equals("/")) {
				result = result / num[i];
			}
			index++;
		}
		return result;
	}
}
