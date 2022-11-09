package baekjoon;

import java.util.*;
import java.io.*;
public class N°úM5_15654 {
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int[] num;
	static boolean[] vis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		vis = new boolean[n];
		num = new int[n];
		
		st = new StringTokenizer(br.readLine());

		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		dfs(0, n, m);
		System.out.println(sb);
	}
	
	static void dfs(int index, int n, int m) {
		if(index == m) {
			for(int i:arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<n; i++) {
			if(vis[i]) continue;
			arr[index] = num[i];
			vis[i] = true;
			dfs(index+1, n, m);
			vis[i] = false;
		}
	}
}
