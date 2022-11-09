package baekjoon;

import java.util.Scanner;

public class N°úM2_15650 {
	static int[] arr;
	static boolean[] vis;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		arr = new int[m];
		vis = new boolean[n+1];
		
		dfs(0, 1, n, m);
	}
	
	static void dfs(int index, int start, int n, int m) {
		if(index == m) {
			for(int i:arr) System.out.printf(i + " ");
			System.out.println();
			return;
		}
		for(int i=start; i<=n; i++) {
			if(vis[i]) continue;
			arr[index] = i;
			vis[i] = true;
			dfs(index+1, i+1, n, m);
			vis[i] = false;
		}
	}
}
