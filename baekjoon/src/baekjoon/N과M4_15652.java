package baekjoon;

import java.io.*;
import java.util.*;

public class N°úM4_15652 {
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		
		dfs(0, 1, n, m);
		System.out.println(sb);

	}
	
	static void dfs(int index, int start, int n, int m) {
		if(index == m) {
			for(int i:arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=start; i<=n; i++) {
			arr[index] = i;
			dfs(index+1, i, n, m);
		}
	}
}
