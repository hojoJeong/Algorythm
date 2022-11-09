package baekjoon;

import java.util.*;
import java.io.*;

public class N°úM3_15651 {
	static int[] arr;
	static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		
		dfs(0, n, m);
		
		System.out.println(sb);
	}
	
	static void dfs(int index, int n, int m) {
		if(index == m) {
			for(int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=n; i++) {
			arr[index] = i;
			dfs(index+1, n, m);
		}
	}
}
