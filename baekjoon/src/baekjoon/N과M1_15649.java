package baekjoon;

import java.util.Scanner;

public class N°úM1_15649 {
	static int[] arr;
	static boolean[] vis;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		arr = new int[m];
		vis = new boolean[n+1];
		
		dfs(0, n, m);
	}
	static void dfs(int index, int n, int m) {
		if(index == m) {
			for(int i=0; i<m; i++) {
				System.out.printf(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i=1; i<=n; i++) {
			if(vis[i]) continue;
			arr[index] = i;
			vis[i] = true;
			dfs(index+1, n, m);
			vis[i] = false;
		}
	}
}